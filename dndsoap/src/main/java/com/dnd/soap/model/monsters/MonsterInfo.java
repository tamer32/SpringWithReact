package com.dnd.soap.model.monsters;

import java.util.HashMap;
import java.util.Map;
import org.springframework.hateoas.RepresentationModel;

public class MonsterInfo extends RepresentationModel<MonsterInfo> {
  String name;
  protected static Map<String, Integer> monsterAttributes = new HashMap<>();

  public String getName() {
    return name;
  }

  public Map<String, Integer> getMonsterAttributes() {
    return monsterAttributes;
  }

  public static void monsterAttributes(Integer strenght, Integer agility, Integer intelect) {
    monsterAttributes.put("Strenght", strenght);
    monsterAttributes.put("Agility", agility);
    monsterAttributes.put("Intelect", intelect);
  }
}
