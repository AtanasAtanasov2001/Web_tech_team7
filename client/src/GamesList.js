import React, { useState, useEffect } from 'react';

function GameList() {
  const [games, setGames] = useState([]);

  useEffect(() => {
    axios
      .then(response => response.json())
      .then(data => setGames(data));
  }, []);

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
