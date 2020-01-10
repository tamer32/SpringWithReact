package com.dnd.soap.model.monsters;

import java.util.Map;

public class Bandit extends MonsterInfo {
  public Map<String, Integer> getStats() {
    monsterAttributes(2, 5, 2);
    return MonsterInfo.monsterAttributes;
  }

  @Override
  public String getName() {
    return "Bandit";
  }
}
