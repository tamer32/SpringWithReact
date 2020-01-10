import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

function Register() {
  return (
    <div>
      <Router>
        <Link to="/register">Register</Link>
      </Router>
    </div>
  );
}
export default Register;
