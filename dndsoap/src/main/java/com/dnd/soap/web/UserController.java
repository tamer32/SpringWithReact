package com.dnd.soap.web;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.dnd.soap.model.PlayerInfoBean;
import com.dnd.soap.model.heroes.PlayerInfo;
import com.dnd.soap.model.monsters.MonsterInfo;
import com.dnd.soap.service.MarketService;
import com.dnd.soap.service.UserManagmentService;

@Controller
public class UserController {

  private UserManagmentService userManagmentService;
  private MarketService marketService;
  private static final String PLAYER_INFO = "playerInfo";
  private Random rand;

  public UserController(UserManagmentService userManagmentService, MarketService marketService)
      throws NoSuchAlgorithmException {
    this.userManagmentService = userManagmentService;
    this.marketService = marketService;
    rand = SecureRandom.getInstanceStrong();
  }

  @ModelAttribute("playerInfo")
  public PlayerInfo getPlayerInfo() {
    return new PlayerInfo();
  }

  @ModelAttribute("monster")
  public MonsterInfo getMonsterInfo() {
    return new MonsterInfo();
  }

  @GetMapping("/")
  public String homePage(Model springModel) {
    return "homePage";
  }

  @PostMapping("/user")
  public String createUser(
      Model theModel,
      @ModelAttribute("playerInfoBean") PlayerInfoBean playerBean,
      @Valid PlayerInfoBean playerInfo,
      BindingResult br) {

    if (br.hasErrors()) {
      return "registerPage";
    }
    marketService.addItem();

    PlayerInfo player = userManagmentService.transformToPlayerInfo(playerBean);
    PlayerInfo newPlayer = userManagmentService.createUser(player);
    theModel.addAttribute(PLAYER_INFO, newPlayer);

    return "redirect:/user/" + newPlayer.getId();
  }

  @GetMapping("/user/{playerId}/market")
  public String marketItems(Model model) {

    model.addAttribute("marketItems", marketService.getItems());

    return "marketPage";
  }

  @GetMapping("/register")
  public String redirectToRegister(
      @ModelAttribute("playerInfoBean") PlayerInfoBean playerInfoBean) {

    return "registerPage";
  }

  @GetMapping("/login")
  public String redirectToLogin() {

    return "login";
  }

  @GetMapping("/user/{playerId}")
  public String userPanel(Model theModel, @PathVariable long playerId) {
    theModel.addAttribute(PLAYER_INFO, userManagmentService.getPlayer(playerId));

    return "characterPage";
  }

  @GetMapping("/fight/{user}")
  public String fightMethod(Model model, @PathVariable long user) {
    int encounter = rand.nextInt(49) + 1;

    model.addAttribute(PLAYER_INFO, userManagmentService.getPlayer(user));
    model.addAttribute("monster", userManagmentService.monsterEncounter(encounter));

    return "fightPage";
  }

  @GetMapping("/modify/{userId}")
  public String goToModifyPage(Model model, @PathVariable long userId) {
    model.addAttribute(PLAYER_INFO, userManagmentService.getPlayer(userId));

    return "modifyPage";
  }

  @PostMapping("/modify/{userId}")
  public String modifyName(
      @PathVariable long userId, @ModelAttribute(PLAYER_INFO) PlayerInfo player, Model model) {
    player = userManagmentService.modifyName(userId, player.getUsername());

    model.addAttribute(PLAYER_INFO, player);

    return "characterPage";
  }
}
