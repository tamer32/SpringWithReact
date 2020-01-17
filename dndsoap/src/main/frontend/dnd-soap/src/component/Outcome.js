import React, { Component } from "react";
import { Button } from "react-bootstrap";

export default class Outcome extends Component {
  constructor(props) {
    super(props);
    this.state = {
      gracefulDice: props.gracefulDice,
      skullDice: props.skullDice,
      message: props.message,
      isLoser: false
    };
  }

  componentDidMount() {
    this.props.outcome.message.includes("victory")
      ? this.setState({ isLoser: false })
      : this.setState({ isLoser: true });
  }

  static getDerivedStateFromProps(props, state) {
    const { outcome } = props;
    if (outcome.message.includes("victory")) {
      return null;
    }
    return {
      ...state,
      isLoser: true
    };
  }

  render() {
    return (
      <div className="col-md-4">
        <table className="table table-striped table-dark">
          <tr>
            <td>Graceful Dice</td>
            <td>{this.props.outcome.gracefulDice}</td>
          </tr>
          <tr>
            <td>Skull Dice</td>
            <td>{this.props.outcome.skullDice}</td>
          </tr>
          <tr>
            <td>Message</td>
            <td>{this.props.outcome.message}</td>
          </tr>
          <tr>
            {!this.state.isLoser && (
              <>
                <td>
                  <Button
                    variant="danger"
                    onClick={this.props.handleFightAgain}
                  >
                    Fight Again!
                  </Button>
                </td>
                <td>
                  <Button
                    variant="success"
                    onClick={() =>
                      window.location.replace(`/user/${this.props.playerId}`)
                    }
                  >
                    To character panel
                  </Button>
                </td>
              </>
            )}
            {console.log("is loser " + this.state.isLoser)}
            {this.state.isLoser && (
              <td>
                <Button
                  onClick={() => {
                    window.location.replace("/");
                    this.props.deleteUser();
                  }}
                >
                  InheritLegacy
                </Button>
              </td>
            )}
          </tr>
        </table>
      </div>
    );
  }
}
