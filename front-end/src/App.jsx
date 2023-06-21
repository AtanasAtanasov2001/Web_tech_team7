import React, { useState } from "react";
import { Container } from "@mui/material";
import Game from "./Game";
import LoginForm from "./LoginForm";
import "./App.css"; 

const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);

  const handleLogin = (username, password) => {
    
    setLoggedIn(true);
  };

  return (
    <Container>
      {loggedIn ? <Game /> : <LoginForm handleLogin={handleLogin} />}
    </Container>
  );
};

export default App;
