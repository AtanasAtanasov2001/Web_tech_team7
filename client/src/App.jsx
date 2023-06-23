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
  const [gameId, setGameId] = useState(""); // New state variable for game ID
  const [createGameInput, setCreateGameInput] = useState(""); // New state variable for CreateGame input

  useEffect(() => {
    if (token) {
      setLoggedIn(true);
    }
  }, [token])

  const handleLogin = (username, password) => {
    axios.post("http://localhost:4000/auth/login", { username, password }, { headers: { 'Content-Type': 'application/json' } })
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
    axios.post("http://localhost:4000/auth/register", { username, password }, { headers: { 'Content-Type': 'application/json' } })
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
    localStorage.removeItem("token");
    setLoggedIn(false);
  };


  const handleCreateGame = () => {
    if (createGameInput) {
      var myId;
      var oponentID;
      axios.get("http://localhost:4000/chess/userByToken", { headers: { 'Content-Type': 'application/json', 'Authorization': localStorage.getItem('token') } })
        .then(mId => {
          myId = mId.data.id;
          axios.get("http://localhost:4000/chess/userByUsername/" + createGameInput, { headers: { 'Content-Type': 'application/json' } })
            .then(opID => {
              oponentID = opID.data.userId;

              axios.post("http://localhost:4000/chess/createGame", { userIdOne:myId, userIdTwo: oponentID }, { headers: { 'Content-Type': 'application/json', 'Authorization': localStorage.getItem('token') } })
                .then(r => {
                  setGameId(r.data.gameId);
                  setShowGamePage(true);
                })
            })
        })
    } else {
      alert("Please enter a game input");
    }
  };


  const handleJoinGame = () => {
    if (gameId) {
      setGameId(gameId);
      setShowGamePage(true);
    } else {
      alert("Please enter a game ID");
    }
  };

  const handleGameIdChange = (event) => {
    setGameId(event.target.value);
  };


  const handleCreateGameInputChange = (event) => {
    setCreateGameInput(event.target.value);
  };

  return (
    <Container>
      <h1 className="login-form">Head 2 Head Chess</h1>
      {loggedIn ? (
        <>
          {!showGamePage ? (
            <>
              <GameList />
              <button onClick={handleLogout} className="logout">
                Logout
              </button>

              <div className="button-group">
                <button onClick={handleCreateGame} className="create">
                  Create Game
                </button>
                <input
                  type="text"
                  value={createGameInput}
                  onChange={handleCreateGameInputChange}
                />
              </div>

              <div className="button-group">
                <button onClick={handleJoinGame} className="join">
                  Join Game
                </button>
                <input
                  type="text"
                  value={gameId}
                  onChange={handleGameIdChange}
                />
              </div>

              {/* <>button on</> */}
            </>
          ) : (
            <>
              {gameId}
              <Game gameId={gameId}/>
              <button onClick={handleLogout} class="logout">Logout</button>
            </>
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
