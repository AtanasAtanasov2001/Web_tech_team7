
import React, { useState, useEffect } from "react";
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
  const [token, setToken] = useState(localStorage.getItem('token'));
  const [showGamePage, setShowGamePage] = useState(false); // New state variable


  useEffect(() => {
    if(token) {
      setLoggedIn(true);
    }
  }, [token])

  const handleLogin = (username, password) => {
    axios.post("http://localhost:4000/auth/login", { username, password },{ headers: { 'Content-Type': 'application/json' } })
      .then(r => {
        if (r.status === 200) {
          localStorage.setItem("token", "5ZvqN3FRsopaOUrSbXbNtyOIr1DVxr88kxEbAs72Hpw7krgWyjo5VmE4YiBIQ9aGN1TO7hQKkXZ4MJPQSo9a35ml8MmO8usMopNugu8xhHznPsz0iGqszdBCu6JnIx8JeSxsQh031lSwOUwzfnQr0DsXLI8AR4RZPDiXmNqCbVU" + r.data.token);
          setLoggedIn(true);
          alert("Success");
        }
      })
      .catch(e => {
        alert(e.response.data)
      })
  };

  const handleRegistration = (username, password) => {
    axios.post("http://localhost:4000/auth/register", { username, password },{ headers: { 'Content-Type': 'application/json' } })
      .then(r => {
        if (r.status === 200) {
          alert("Success");
          setShowRegistration(false);
        }
      })
      .catch(e => {
        alert(e.response.data);
      });
  };

  const handleLogout = () => {
    setLoggedIn(false);
  };


  const handleCreateGame = () => {
    setShowGamePage(true); // Set the state to display the game page
  };

  return (
    <Container>
      <h1 className="login-form">Head 2 Head Chess</h1>
      {loggedIn ? (
        <>
          {!showGamePage ? (
            <>
              <GameList />
              <button onClick={handleLogout}>Logout</button>
              <button onClick={handleCreateGame}>Create Game</button>
              {/* <>button on</> */}
            </>
          ) : (
            <Game />
          )}
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