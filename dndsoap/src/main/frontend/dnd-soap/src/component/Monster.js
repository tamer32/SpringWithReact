import React from "react";
function Monster(props) {
  return (
    <tr>
      {props.monster.stats && (
        <>
          <td>{props.monster.name}</td>
          <td>Monster</td>
          <td>{props.monster.stats["Strenght"]}</td>
          <td>{props.monster.stats["Agility"]}</td>
          <td>{props.monster.stats["Intelect"]}</td>
        </>
      )}
    </tr>
  );
}

export default Monster;
