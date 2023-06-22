import React, { useState } from "react";
import { Container } from "@mui/material";
import Game from "./Game";
import LoginForm from "./LoginForm";
import RegistrationForm from "./RegistrationForm";
import GameList from "./GamesList";
import "./App.css";
import axios from "axios";

const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [showRegistration, setShowRegistration] = useState(false);

  const handleLogin = (username, password) => {
    setLoggedIn(true);
  };

  const handleRegistration = (username, password) => {
    setLoggedIn(true);
    setShowRegistration(false);

    axios.post("http://localhost:4000/auth/mockRegister", {
      username: "111",
      password: "111"
    }, { headers: { 'Content-Type': 'application/json' } }).then(r => 
      { console.log(r) 
      
      
      })

  };

  return (
    <Container>
      <h1 className="login-form">Head 2 Head Chess</h1>
      {loggedIn ? (
        <>
          <GameList /> {/* Render the GameList component */}
        </>
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

export default App;
