package com.dnd.soap.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dnd.soap.model.heroes.PlayerInfo;
import com.dnd.soap.model.monsters.MonsterInfo;
import com.dnd.soap.service.UserManagmentService;
import com.dnd.soap.web.UserController;

@RestController
@RequestMapping
public class RestApi {
  private UserManagmentService userManagmentService;
  private SecureRandom rand = new SecureRandom();

  @Autowired
  public RestApi(UserManagmentService userManagmentService) {
    this.userManagmentService = userManagmentService;
  }

  public RestApi() {}

  @GetMapping(value = "/player-battles/{playerId}-")
  public ResponseEntity<Map<String, String>> battlePhase(@PathVariable Long playerId) {
    Link checkEncounterLink =
        linkTo(UserController.class).slash("user").slash(playerId).withSelfRel();

    return ResponseEntity.created(checkEncounterLink.toUri())
        .body((userManagmentService.battleCalculator(playerId)));
  }

  @GetMapping(value = "/new-encounter")
  public ResponseEntity<MonsterInfo> checkEncounter() {
    int encounter = rand.nextInt(49) + 1;
    MonsterInfo monster = userManagmentService.monsterEncounter(encounter);
    Link link = linkTo(methodOn(RestApi.class).checkEncounter()).withSelfRel();
    monster.add(link);
    return new ResponseEntity<>(monster, HttpStatus.OK);
  }

  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PlayerInfo> login(@RequestParam String username) {

    return ResponseEntity.ok().body(userManagmentService.login(username));
  }

  @GetMapping
  public ResponseEntity<List<PlayerInfo>> listAll() {
    return ResponseEntity.ok(userManagmentService.listUsers());
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable long userId, Model model) {
    userManagmentService.deleteUser(userId);
    Link linkToRegister = new Link("http://localhost:8080/register/").withRel("register");
    return ResponseEntity.created(linkToRegister.toUri()).build();
  }
}
