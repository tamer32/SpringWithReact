package com.dnd.soap.model.monsters;

import java.util.Map;

public class Dragon extends MonsterInfo {

  public Map<String, Integer> getStats() {
    monsterAttributes(35, 35, 45);
    return MonsterInfo.monsterAttributes;
  }

  @Override
  public String getName() {
    return "Dragon (You are quite unlucky...)";
  }
}
