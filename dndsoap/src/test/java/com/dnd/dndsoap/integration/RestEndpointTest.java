package com.dnd.dndsoap.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.dnd.soap.DndSoapApplication;
import com.dnd.soap.model.heroes.PlayerInfo;
import com.dnd.soap.model.monsters.MonsterInfo;
import com.dnd.soap.model.monsters.VajirSorceress;
import com.dnd.soap.service.UserManagmentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DndSoapApplication.class)
@AutoConfigureMockMvc
public class RestEndpointTest {
  @Autowired private MockMvc mockMvc;
  @Autowired private UserManagmentService userService;

  @Test
  public void testDeleteUser() throws Exception {
    PlayerInfo player = new PlayerInfo("Pesho", "Warrior");
    userService.createUser(player);

    mockMvc.perform(delete("/delete/" + player.getId())).andExpect(status().is2xxSuccessful());
    assertThat(userService.listUsers()).isEmpty();
  }

  @Test
  public void testCheckEncounter() throws Exception {
    MonsterInfo monster = userService.monsterEncounter(23);
    mockMvc.perform(get("/newEncounter")).andExpect(status().is2xxSuccessful());
    System.out.println(monster.getClass());
    assertThat(monster).isInstanceOf(VajirSorceress.class);
  }
}
