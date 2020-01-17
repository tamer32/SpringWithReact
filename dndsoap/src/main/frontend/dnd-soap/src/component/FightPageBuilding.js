import React, { Component } from "react";
import Monster from "./Monster";
import { Button } from "react-bootstrap";
import Player from "./Player";
import Outcome from "./Outcome";
export default class FightPageBuilding extends Component {
  constructor() {
    super();
    this.state = {
      monster: {},
      outcome: {},
      showOutcome: false,
      player: { playerId: window.location.pathname.split("/")[2], battleId: 0 }
    };
    this.getMonster = this.getMonster.bind(this);
    this.battle = this.battle.bind(this);
  }
  componentDidMount() {
    this.getMonster();
    this.battle();
  }

  getMonster() {
    fetch(`/monster`)
      .then(data => data.json())
      .then(data => {
        this.setState({ monster: data });
      });
  }
  battle() {
    fetch(
      `/player-battles/${this.state.player.playerId}-${this.state.player
        .battleId + 1}`
    )
      .then(data => data.json())
      .then(data => this.setState({ outcome: data }));
  }
  fightAgain() {
    this.getMonster();
    this.battle();
  }
  deleteUser() {
    fetch(`/${this.state.player.playerId}`, { method: "DELETE" });
  }
  render() {
    return (
      <div className="col-md-12,table-responsive">
        <table className="table table-striped table-dark">
          <thead>
            <tr>
              <th> Name</th>
              <th> Class</th>
              <th> Strenght </th>
              <th> Agility</th>
              <th> Intelect</th>
            </tr>
          </thead>
          <tbody>
            <Player />
            <Monster monster={this.state.monster} />
          </tbody>
          <Button
            variant="danger"
            disabled={this.state.showOutcome}
            onClick={() => this.setState({ showOutcome: true })}
          >
            Roll the dice!
          </Button>
        </table>
        {this.state.showOutcome && (
          <Outcome
            outcome={this.state.outcome}
            handleFightAgain={this.fightAgain.bind(this)}
            deleteUser={this.deleteUser.bind(this)}
            playerId={this.state.player.playerId}
          />
        )}
      </div>
    );
  }
}
