import React, { useState } from "react";
import { Container } from "@mui/material";
import Game from "./Game";
import LoginForm from "./LoginForm";
import "./App.css"; // Import the App.css file

const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);

  const handleLogin = (username, password) => {
    // Perform login authentication
    // Here you can check the provided username and password
    // against your authentication system or API

    // For demonstration purposes, let's assume the login is successful
    setLoggedIn(true);
  };

  return (
    <Container>
      {loggedIn ? <Game /> : <LoginForm handleLogin={handleLogin} />}
    </Container>
  );
};

export default App;
