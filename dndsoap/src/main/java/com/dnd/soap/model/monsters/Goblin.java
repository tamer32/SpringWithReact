package com.dnd.soap.model.monsters;

import java.util.Map;

public class Goblin extends MonsterInfo {

  public Map<String, Integer> getStats() {
    monsterAttributes(4, 3, 2);
    return MonsterInfo.monsterAttributes;
  }

  @Override
  public String getName() {
    return "Goblin";
  }
}
