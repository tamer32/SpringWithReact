import React, { useState, useEffect } from "react";
function Player() {
  const playerId = window.location.pathname.split("/")[2];
  const [playerState, setPlayerState] = useState({});

  useEffect(() => {
    console.log("Here");
    fetch(`/players/${playerId}`)
      .then(data => data.json())
      .then(data => setPlayerState(data))
      .catch(error => console.log(error));
  }, [playerId]);

  return (
    <tr>
      {playerState.playerAttributes && (
        <>
          <td>{playerState.username}</td>
          <td>{playerState.playerClass}</td>
          <td>{playerState.playerAttributes["strenght"]}</td>
          <td>{playerState.playerAttributes["agility"]}</td>
          <td>{playerState.playerAttributes["intelect"]}</td>
        </>
      )}
    </tr>
  );
}
export default Player;
