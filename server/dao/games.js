const axios = require('axios');
const path = require("path");
require('dotenv').config({ path: path.resolve(__dirname, '../../.env') });
const DB_SERVER = process.env.ENV === 'prod' ? process.env.DB_SERVER : 'localhost';
// fen format ->
// eg: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
// FEN stands for Forsyth-Edwards Notation, and it is a compact notation used to describe the state of a game of chess. FEN strings are commonly used in computer chess programs, chess databases, and online chess games.

// A FEN string consists of six fields, separated by spaces. The fields describe, in order:

// The position of the pieces on the board, starting with the eighth rank and ending with the first rank. The pieces are represented by letters: "K" for king, "Q" for queen, "R" for rook, "B" for bishop, "N" for knight, and "P" for pawn. Empty squares are represented by a number representing the count of consecutive empty squares, and the total number of empty squares is omitted.
// The side to move ("w" for white or "b" for black).
// Castling availability. If neither side can castle, this field is "-". Otherwise, it is a combination of the letters "K", "Q", "k", and "q" representing whether each side can castle kingside or queenside.
// En passant target square. If there is no en passant target square, this field is "-". Otherwise, it is a square in algebraic notation where a pawn can capture en passant.
// Halfmove clock. This is the number of halfmoves since the last capture or pawn advance. It is used to determine whether a draw can be claimed under the 50-move rule.
// Fullmove number. This is the number of the current move, starting at 1 and incremented after black's move.

/**
 * get game state
 * @param {string} gameId - game id
 * @returns {promise} - eg. { "gameId": "...", "state": "..."}
 */
function getGameState(gameId) {
  const url = `http://${DB_SERVER}:8080/state/${gameId}/currentState`;

	return axios.get(url)
		.then(res => {return {gameId, state: res.data}})
		.catch(e => {
      console.error(`ERROR: Game id ${gameId} not found!`)
			throw new Error(`Game id ${gameId} not found!`);
		});
    
}
/**
 * get games 
 * @param {none} gameId - game id
 * @returns {promise} - eg. { "gameId": "...", "state": "..."}
 */
function getGames() {
  const url = `http://localhost:8080/game`;

	return axios.get(url)
		.then(res => { console.log(res.data)
                  res.data })
		.catch(e => {
      console.error(`ERROR: Game  not found!`)
			throw new Error(`Game not found!`);
		});
}
/**
 * create game
 * @param {object} data - object containing game state as string (fen) and user ids,
 * @returns {promise} - eg. { "gameId": "..." }
 */
function createGame(data) {
  const { userIdOne, userIdTwo, fen, token } = data;

  const url = `http://${DB_SERVER}:8080/game`;

  let config = {
    headers: {
      'Content-Type': 'application/json',
      'Authorization' : token
    }
  }

  let body = {
    "whiteId": userIdOne,
    "blackId": userIdTwo,
    "state": fen
  }

	return axios.post(url, body, config)
		.then(res => { return { gameId: res.data } })
		.catch(e => {
      console.error(`ERROR: Authorization token not valid!`)
			throw new Error(`Authorization token not valid!`);
    });
}

/**
 * update game
 * @param {string} gameId - game id
 * @param {object} data - object containing game state as string (fen)
 * @returns {promise} - {}
 */
function updateGame(gameId, data) {
  const url = `http://${DB_SERVER}:8080/state/update`;

  let config = {
    headers: {
      'Content-Type': 'application/json',
      'Authorization' : data.token
    }
  }

  let body = {
    "gameId": gameId,
    "state": data.fen
  }

	return axios.post(url, body, config)
		.then(res => res.data)
		.catch(e => {
      console.error(`ERROR: Authorization token not valid!`)
			throw new Error(`Authorization token not valid!`);
    });
}

const gamesDAO = { getGameState, createGame, updateGame, getGames}

module.exports = gamesDAO;
