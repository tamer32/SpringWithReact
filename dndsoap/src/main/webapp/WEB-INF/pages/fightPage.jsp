<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@taglib tagdir="/WEB-INF/tags" prefix="tf"%> <%@
taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <%@ page
isELIgnored="false"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<tf:layout>
  <h1>Prepare to fight!</h1>
  <div id="fightPage">
  </div>
  <script src ="/gen-js/figtPage.js"></script>
  <div class="row">
    <div id="encounterDiv" style="width: 242">
      <div class="col-md-6">
        <table class="table table-striped table-dark">
          <tr>
            <th>Name</th>
            <th>Class</th>
            <th>Strenght</th>
            <th>Agility</th>
            <th>Intelect</th>
          </tr>

          <tr>
            <td>${playerInfo.username}</td>
            <td>${playerInfo.playerClass}</td>
            <c:forEach
              items="${playerInfo.playerAttributes}"
              var="playerStats"
              varStatus="status"
            >
              <td>${playerStats.value}</td>
            </c:forEach>
          </tr>
          <tr>
            <td id="monsterName">${monster.name}</td>
            <td>Monster</td>
            <c:set var="count" value="0" scope="page" />
            <c:forEach
              items="${monster.stats}"
              var="monsterStats"
              begin="0"
              end="3"
              varStatus="loop"
            >
              <td id="monsterStats${count}">${monsterStats.value}</td>
              <c:set var="count" value="${count+1}" scope="page" />
            </c:forEach>
          </tr>
          <tr>
            <td
              ><input
                type="button"
                onclick="battlePhase()"
                value="Roll the dice!"
                id="rollTheDice"
            /></td>
          </tr>
        </table>
      </div>
    </div>
    <div class="col-md-6">
      <div
        class="d-flex justify-content-start"
        id="diceDiv"
        style="visibility: hidden"
      >
        <table class="table table-striped table-dark">
          <tr>
            <td id="gracefulDice"></td>
          </tr>
          <tr>
            <td id="skullDice"></td>
          </tr>
          <tr>
            <td id="battleOutcome"></td>
          </tr>
          <tr>
            <td
              ><input
                type="button"
                value="Character Screen"
                style="visibility: hidden"
                id="showCharacter"
                onclick="redirectToCharacterScreen()"
            /></td>

            <td
              ><input
                type="button"
                style="visibility: hidden"
                onclick="battlePhase()"
                value="FightAgain!"
                id="fightAgain"
            /></td>

            <td
              ><input
                type="button"
                value="Inherit the legacy"
                style="visibility: hidden"
                id="createNewCharacter"
                onclick="deleteUser()"
            /></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</tf:layout>
