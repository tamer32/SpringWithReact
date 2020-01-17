import React from "react";
import "./App.css";
import Home from "./component/Home.js";
import Header from "./component/Header.js";
import { Router, Switch, Route } from "react-router";

function App() {
  return (
    <div className="App">
      <div>
        <Header />
      </div>
      <div>
        <Home />
      </div>
    </div>
  );
}

export default App;
