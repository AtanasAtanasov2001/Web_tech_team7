import React, { useState, useEffect } from 'react';
import axios from 'axios';

function GameList() {
  const [games, setGames] = useState([]);

  useEffect(() => {
    fetchGames();
  }, []);

  const fetchGames = async () => {
    try {
      const response = await axios.get('http://localhost:4000/chess/games'); // Replace with the endpoint to fetch games from your backend
      setGames(response.data);
    } catch (error) {
      console.error('Error fetching games:', error);
    }
  };

  return (
    <div>
      {/* Render the list of games */}
      {games.map(game => (
        <div key={game.id}>{game.name}</div>
      ))}
    </div>
  );
}

export default GameList;
