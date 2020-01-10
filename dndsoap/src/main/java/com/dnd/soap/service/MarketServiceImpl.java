package com.dnd.soap.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dnd.soap.model.Item;
import com.dnd.soap.model.heroes.PlayerInfo;

@WebService(
    endpointInterface = "com.dnd.soap.service.MarketService",
    wsdlLocation = "target/generated/wsdl/MarketService.wsdl")
@Service
public class MarketServiceImpl implements MarketService {
  private Map<String, Item> items = new ConcurrentHashMap<>();

  @Autowired UserManagmentService userService;

  @Override
  public void addItem() {
    items.put("Short sword", new Item("Short sword", 3, 0, 0));
    items.put("Dagger", new Item("Dagger", 2, 4, 0));
  }

  @Override
  public Collection<Item> getItems() {
    return items.values();
  }

  @Override
  public Item getItemById(String itemName) {
    return items.get(itemName);
  }

  @Override
  public PlayerInfo getPlayer(long id) {
    return userService.getPlayer(id);
  }

  @Override
  public void removeItem(String itemName) {
    items.remove(itemName);
  }
}
