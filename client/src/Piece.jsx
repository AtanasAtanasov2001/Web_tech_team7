import React from 'react'

export default function Piece({ piece: { type, color } }) {
    const piecePng = require(`./resources/${type}_${color}.png`)
    return (
        <div className='piece-container'>
            <img src={piecePng} alt="" className='piece' />
        </div>
    )
}