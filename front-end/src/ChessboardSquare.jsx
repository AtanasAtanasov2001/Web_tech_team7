import React from 'react'
import Piece from './Piece'
import Square from './Square'

export default function ChessboardSquare({ piece, black }) {
    return (
        <div className='board-square'>
            <Square black={black}>
                {piece && <Piece piece={piece} />}
            </Square>
        </div>
    )
} 