package com.dnd.soap.model.monsters;

import java.util.Map;

public class VajirSorceress extends MonsterInfo {
  public Map<String, Integer> getStats() {
    monsterAttributes(2, 4, 10);
    return MonsterInfo.monsterAttributes;
  }

  @Override
  public String getName() {
    return "Vaj'ir Sorceress";
  }
}
