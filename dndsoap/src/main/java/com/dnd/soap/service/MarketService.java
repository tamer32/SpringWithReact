package com.dnd.soap.service;

import java.util.Collection;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.dnd.soap.model.Item;
import com.dnd.soap.model.heroes.PlayerInfo;

@WebService
@SOAPBinding
public interface MarketService {
  @WebMethod
  public void addItem();

  @WebMethod
  public Collection<Item> getItems();

  @WebMethod
  public Item getItemById(String itemName);

  @WebMethod
  public void removeItem(String itemName);

  @WebMethod
  public PlayerInfo getPlayer(long id);
}
