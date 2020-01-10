package com.dnd.soap.service;

import java.util.List;
import java.util.Map;
import com.dnd.soap.model.PlayerInfoBean;
import com.dnd.soap.model.heroes.PlayerInfo;
import com.dnd.soap.model.monsters.MonsterInfo;

/** @author p.siderov */
public interface UserManagmentService {

  public PlayerInfo createUser(PlayerInfo player);

  public MonsterInfo monsterEncounter(Integer encounter);

  public void deleteUser(long playerId);

  public PlayerInfo transformToPlayerInfo(PlayerInfoBean playerBean);

  public PlayerInfo getPlayer(long playerId);

  public PlayerInfo modifyName(long id, String player);

  public Integer calculatePowerRatio(
      MonsterInfo monster, PlayerInfo player, Integer skullDice, Integer gracefulDice);

  public Map<String, String> battleCalculator(long playerId);

  public List<PlayerInfo> listUsers();

  public PlayerInfo login(String username);
}
