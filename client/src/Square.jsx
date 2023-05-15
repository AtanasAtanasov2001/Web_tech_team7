import React from 'react'

export default function Square({ children, black }) {
    const backgroundClass = black ? 'square-black' : 'square-white'

    return (
        <div className={`${backgroundClass} board-square`}>
            {children}
        </div>
    )
}
