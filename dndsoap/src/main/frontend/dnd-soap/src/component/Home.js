import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import Login from "./Login.js";
import Register from "./Register.js";
function Home() {
  const getReq = () => fetch(window.location.href);
  const [isLoginVisible, setLoginVisible] = useState(false);
  return (
    <div>
      <a
        href="#"
        onClick={e => {
          e.preventDefault();
          setLoginVisible(!isLoginVisible);
        }}
      >
        Login
      </a>
      <Register />
      {isLoginVisible && <Login />}
    </div>
  );
}
export default Home;
