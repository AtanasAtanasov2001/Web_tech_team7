import { useState, useMemo, useCallback } from "react";
import { Chessboard } from "react-chessboard";
import { Chess } from "chess.js";
import CustomDialog from "./CustomDialog";
import "./App.css";

function Game({ players, room, orientation, cleanup }) {
  const chess = useMemo(() => new Chess(), []);
  const [fen, setFen] = useState(chess.fen());
  const [over, setOver] = useState("");

  const makeAMove = useCallback(
    (move) => {
      try {
        const result = chess.move(move);
        setFen(chess.fen());

        console.log("over, checkmate", chess.isGameOver(), chess.isCheckmate());

        if (chess.isGameOver()) {
          if (chess.isCheckmate()) {
            setOver(`Checkmate! ${chess.turn() === "w" ? "black" : "white"} wins!`);
          } else if (chess.isDraw()) {
            setOver("Draw");
          } else {
            setOver("Game over");
          }
        }

        return result;
      } catch (e) {
        return null;
      }
    },
    [chess]
  );

  const handlePlayAgain = () => {
    chess.reset(); 
    setFen(chess.fen()); 
    setOver(""); 
  };

  function onDrop(sourceSquare, targetSquare) {
    const moveData = {
      from: sourceSquare,
      to: targetSquare,
      color: chess.turn(),
    };

    const move = makeAMove(moveData);

    if (move === null) return false;

    return true;
  }

  return (
    <div className="game-container">
      <div className="board">
        <Chessboard position={fen} onPieceDrop={onDrop} />
      </div>
      <CustomDialog
        open={Boolean(over)}
        title={over}
        contentText={over}
        handleContinue={() => {
          setOver("");
        }}
        handlePlayAgain={handlePlayAgain} 
        className="dialog"
      />
    </div>
  );
}

export default Game;
