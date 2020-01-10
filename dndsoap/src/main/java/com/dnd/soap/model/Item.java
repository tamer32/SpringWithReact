package com.dnd.soap.model;

public class Item {

  private String name;
  private Integer strenght;
  private Integer agility;
  private Integer intelect;

  public Item(String name, Integer strenght, Integer agility, Integer intelect) {
    this.name = name;
    this.strenght = strenght;
    this.agility = agility;
    this.intelect = intelect;
  }

  public Item() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStrenght() {
    return strenght;
  }

  public void setStrenght(Integer strenght) {
    this.strenght = strenght;
  }

  public Integer getAgility() {
    return agility;
  }

  public void setAgility(Integer agility) {
    this.agility = agility;
  }

  public Integer getIntelect() {
    return intelect;
  }

  public void setIntelect(Integer intelect) {
    this.intelect = intelect;
  }

  @Override
  public String toString() {
    return "Item ["
        + ", name="
        + name
        + ", strenght="
        + strenght
        + ", agility="
        + agility
        + ", intelect="
        + intelect
        + "]";
  }
}
