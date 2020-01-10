package com.dnd.soap.model.monsters;

import java.util.Map;
import java.util.stream.IntStream;

public class Werewolf extends MonsterInfo {
  int[] monsterIDs = IntStream.range(26, 30).toArray();

  public Map<String, Integer> getStats() {
    monsterAttributes(10, 12, 4);
    return MonsterInfo.monsterAttributes;
  }

  @Override
  public String getName() {
    return "Werewolf";
  }
}
