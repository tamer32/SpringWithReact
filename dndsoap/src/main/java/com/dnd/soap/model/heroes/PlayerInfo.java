package com.dnd.soap.model.heroes;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlayerInfo {
  private Long id;
  private Long battleId;

  @NotBlank
  @Size(min = 2, max = 32)
  private String username;

  private Integer exp;

  @NotNull @NotEmpty String playerClass;
  private Map<String, Integer> playerAttributes = new HashMap<>();

  public Long getBattleId() {
    return battleId;
  }

  public void setBattleId(Long battleId) {
    this.battleId = battleId;
  }

  public Map<String, Integer> getPlayerAttributes() {
    return playerAttributes;
  }

  public void setPlayerAttributes(Map<String, Integer> playerAttributes) {
    this.playerAttributes = playerAttributes;
  }

  public String getPlayerClass() {
    return playerClass;
  }

  @Override
  public String toString() {
    return "PlayerInfo [username=" + username + ", playerClass=" + playerClass + "]";
  }

  public void setPlayerClass(String playerClass) {
    this.playerClass = playerClass;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String name) {
    this.username = name;
  }

  public Integer getExp() {
    return exp;
  }

  public void setExp(Integer exp) {
    this.exp = exp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PlayerInfo(String username, String playerClass) {
    this.username = username;
    this.playerClass = playerClass;
  }

  public PlayerInfo() {}

  public void setStats(Integer strenght, Integer agility, Integer intelect) {
    playerAttributes.put("strenght", strenght);
    playerAttributes.put("agility", agility);
    playerAttributes.put("intelect", intelect);
  }

  public void setAttributes() {
    setStats(0, 0, 0);
  }

  public void levelUp(Integer bonusStrenght, Integer bonusAgility, Integer bonusIntelect) {
    playerAttributes.replace("Strenght", playerAttributes.get("Strenght") + bonusStrenght);
    playerAttributes.replace("Agility", playerAttributes.get("Agility") + bonusAgility);
    playerAttributes.replace("Intelect", playerAttributes.get("Intelect") + bonusIntelect);
  }
}
