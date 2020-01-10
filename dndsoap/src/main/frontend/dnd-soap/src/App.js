import React from "react";
import "./App.css";
import "./component/component.js";
import printH1 from "./component/component.js";
import Register from "./component/Register.js";
import Header from "./component/Header.js";

function App() {
  return (
    <div className="App">
      <div>
        <Header />
      </div>
      <div>
        <Register />
      </div>
    </div>
  );
}

export default App;
