
import React, { useState } from "react";
import { Container } from "@mui/material";
import Game from "./Game";
import LoginForm from "./LoginForm";
import RegistrationForm from "./RegistrationForm";
import "./App.css";


const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [showRegistration, setShowRegistration] = useState(false);

  const handleLogin = (username, password) => {
    setLoggedIn(true);
  };

  const handleRegistration = (username, password) => {
    setLoggedIn(true);
    setShowRegistration(false);
  };

  return (
    <Container>
      <h1 className="login-form">Head 2 Head Chess</h1>
      {loggedIn ? (
        <Game />
      ) : (
        <>
          {showRegistration ? (
            <RegistrationForm handleRegistration={handleRegistration} />
          ) : (
            <LoginForm handleLogin={handleLogin} />
          )}
          <button onClick={() => setShowRegistration(!showRegistration)}>
            {showRegistration ? "Back to Login" : "Register"}
          </button>
        </>
      )}
    </Container>
  );
};

export default App

