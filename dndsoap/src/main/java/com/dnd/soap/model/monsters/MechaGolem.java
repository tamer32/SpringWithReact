package com.dnd.soap.model.monsters;

import java.util.Map;

public class MechaGolem extends MonsterInfo {
  public Map<String, Integer> getStats() {
    monsterAttributes(32, 4, 0);
    return MonsterInfo.monsterAttributes;
  }

  @Override
  public String getName() {
    return "Mecha Golem";
  }
}
