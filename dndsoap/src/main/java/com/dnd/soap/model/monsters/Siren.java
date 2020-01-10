package com.dnd.soap.model.monsters;

import java.util.Map;

public class Siren extends MonsterInfo {
  public Map<String, Integer> getStats() {
    monsterAttributes(6, 12, 18);
    return MonsterInfo.monsterAttributes;
  }

  @Override
  public String getName() {
    return "Siren";
  }
}
