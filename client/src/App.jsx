import React, { useEffect, useState } from 'react'
import './App.css';
import { gameSubject } from './Game'
import Chessboard from './Chessboard'
import SocketComponent from './SocketComponent';

function App() {
  const [board, setBoard] = useState([])
  useEffect(() => {
    const subscribe = gameSubject.subscribe(game => setBoard(game.board))
    return () => subscribe.unsubscribe()
  }, [])

  return (
    <div className='container'>
      <div className='board-container'>
        <Chessboard board={board} />
        <SocketComponent />
      </div>
    </div>
  )
}

export default App