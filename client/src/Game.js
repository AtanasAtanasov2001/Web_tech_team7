import { useState, useMemo, useCallback, useEffect } from "react";
import { Chessboard } from "react-chessboard";
import { Chess } from "chess.js";
import CustomDialog from "./CustomDialog";
import "./App.css";
import axios from "axios";


function Game({ players, room, orientation, cleanup }) {
    const chess = useMemo(() => new Chess(), []);
    const [fen, setFen] = useState(chess.fen());
    const [over, setOver] = useState("");
    const gameId = 'df475ac5-44dd-4314-b674-cb288e96ccba';

    useEffect(() => {
        var fen_;
        axios.get("http://localhost:4000/chess/game/" + gameId)
            .then(r => {
                fen_ = r.data.state;
                chess.load(fen_);
                setFen(fen_);
            })
    }, [chess, fen])

    const makeAMove = useCallback(
        (move) => {
            try {
                axios.post("http://localhost:4000/chess/move", {fen: chess.fen(), from: move.from, to: move.to, gameId }, { headers: { 'Content-Type': 'application/json', 'Authorization': localStorage.getItem('token') } })
                    .then(r => {
                        console.log(r);
                        setFen(r.data.after);
                    })
                    .catch(e => {
                        console.log("invalid move");
                    })

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
            promotion: "q",
            promotion: 'r',
            promotion: 'b',
            promotion: 'n',
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
