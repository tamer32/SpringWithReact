package com.dnd.soap.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.dnd.soap.model.PlayerInfoBean;
import com.dnd.soap.model.heroes.Bowman;
import com.dnd.soap.model.heroes.PlayerInfo;
import com.dnd.soap.model.heroes.Rogue;
import com.dnd.soap.model.heroes.Sorcerer;
import com.dnd.soap.model.heroes.Warrior;
import com.dnd.soap.model.monsters.Bandit;
import com.dnd.soap.model.monsters.Dragon;
import com.dnd.soap.model.monsters.Elemental;
import com.dnd.soap.model.monsters.Goblin;
import com.dnd.soap.model.monsters.MechaGolem;
import com.dnd.soap.model.monsters.MonsterInfo;
import com.dnd.soap.model.monsters.VajirSorceress;
import com.dnd.soap.model.monsters.Werewolf;
import com.dnd.soap.model.monsters.Zombie;

@Service
public class UserManagmentServiceImpl implements UserManagmentService {

  private List<PlayerInfo> users = Collections.synchronizedList(new LinkedList<>());
  private MonsterInfo monster;
  private Random rand;

  public UserManagmentServiceImpl() throws NoSuchAlgorithmException {
    rand = SecureRandom.getInstanceStrong();
  }

  @Override
  public PlayerInfo createUser(PlayerInfo player) {
    player.setExp(0);
    player.setId(Calendar.getInstance().getTimeInMillis());
    player.setAttributes();
    player.setBattleId(0L);
    users.add(player);
    return player;
  }

  @Override
  public void deleteUser(long playerId) {
    users =
        Collections.synchronizedList(
            users.stream().filter(u -> u.getId() != playerId).collect(Collectors.toList()));
  }

  @Override
  public List<PlayerInfo> listUsers() {
    return users;
  }

  @Override
  public MonsterInfo monsterEncounter(Integer encounter) {
    if (isBetween(encounter, 1, 10)) {
      monster = new Goblin();
    } else if (isBetween(encounter, 11, 14)) {
      monster = new Bandit();
    } else if (isBetween(encounter, 15, 20)) {
      monster = new Zombie();
    } else if (isBetween(encounter, 21, 27)) {
      monster = new VajirSorceress();
    } else if (isBetween(encounter, 28, 35)) {
      monster = new Werewolf();
    } else if (isBetween(encounter, 35, 41)) {
      monster = new MechaGolem();
    } else if (isBetween(encounter, 42, 46)) {
      monster = new Elemental();
    } else if (isBetween(encounter, 47, 50)) {
      monster = new Dragon();
    }
    return monster;
  }

  @Override
  public PlayerInfo login(String username) {
    return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElseThrow();
  }

  @Override
  public PlayerInfo transformToPlayerInfo(PlayerInfoBean playerBean) {
    PlayerInfo player;
    if (playerBean.getPlayerClass().equals("Warrior")) {
      player = new Warrior();
    } else if (playerBean.getPlayerClass().equals("Bowman")) {
      player = new Bowman();
    } else if (playerBean.getPlayerClass().equals("Rogue")) {
      player = new Rogue();
    } else if (playerBean.getPlayerClass().equals("Sorcerer")) {
      player = new Sorcerer();
    } else {
      player = new PlayerInfo();
    }

    player.setUsername(playerBean.getUsername());
    player.setPlayerClass(playerBean.getPlayerClass());
    return player;
  }

  @Override
  public Map<String, String> battleCalculator(long playerId, Long battleId) {
    Integer gracefulDice = rand.nextInt(5) + 1;
    Integer skullDice = rand.nextInt(5) + 1;
    String message = "";
    Map<String, String> map = new HashMap<>();
    PlayerInfo player = getPlayer(playerId);
    Integer battleScore = calculatePowerRatio(monster, player, skullDice, gracefulDice);

    map.put("gracefulDice", gracefulDice.toString());
    map.put("skullDice", skullDice.toString());

    if (battleScore < 0) {
      getPlayer(playerId).setExp(player.getExp() + 1);
      message = "Congratulations on your victory";
      map.put("playerExp", player.getExp().toString());
    } else if (battleScore == 0) {
      message =
          "You almost defeat the vile beast, but with it's last drop of luck managed to escape!";
    } else if (battleScore > 0) {
      message =
          "This time the fate wasn't on your side and you lost to the beast and your hero died miserably.Create a new "
              + "character and go avenge your fallen hero!";
    }
    battleId += 1;
    map.put("message", message);
    map.put("battleId", battleId.toString());

    return map;
  }

  @Override
  public Integer calculatePowerRatio(
      MonsterInfo monster, PlayerInfo player, Integer skullDice, Integer gracefulDice) {
    Integer monsterStatsSum =
        ((monster.getMonsterAttributes().get("Strenght")
                + monster.getMonsterAttributes().get("Agility")
                + monster.getMonsterAttributes().get("Intelect"))
            * skullDice);
    Integer playerStatsSum =
        ((player.getPlayerAttributes().get("strenght")
                + player.getPlayerAttributes().get("agility")
                + player.getPlayerAttributes().get("intelect"))
            * gracefulDice);

    return monsterStatsSum - playerStatsSum;
  }

  @Override
  public PlayerInfo modifyName(long playerId, String newUsername) {
    getPlayer(playerId).setUsername(newUsername);

    return getPlayer(playerId);
  }

  @Override
  public PlayerInfo getPlayer(long playerId) {
    return users
        .stream()
        .filter(user -> user.getId() == playerId)
        .findFirst()
        .orElse(new PlayerInfo());
  }

  public boolean isBetween(int x, int lower, int upper) {
    return lower <= x && x <= upper;
  }
}
