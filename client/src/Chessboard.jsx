import React from 'react'
import ChessboardSquare from './ChessboardSquare'


export default function Chessboard({ board }) {
    const NUM_ROWS = 8;
    const NUM_COLS = 8;
    function getXYPosition(item) {
        const x = item % NUM_COLS
        const y = Math.abs(Math.floor(item / NUM_COLS) - NUM_ROWS - 1)
        return { x, y }
    }
    function isBlack(item) {
        const { x, y } = getXYPosition(item)
        return (x + y) % 2 === 1
    }

    return (
        <div className='board'>
            {board.flat().map((piece, item) => (
                <div key={item} className='square'>
                    <ChessboardSquare piece={piece} black={isBlack(item)} />
                </div>
            ))}
        </div>
    )
}