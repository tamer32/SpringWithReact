package com.dnd.soap.model.monsters;

import java.util.Map;

public class Zombie extends MonsterInfo {
  public Map<String, Integer> getStats() {
    monsterAttributes(10, 1, 0);
    return MonsterInfo.monsterAttributes;
  }

  @Override
  public String getName() {
    return "Zombie";
  }
}
