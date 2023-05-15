const Chess = require('chess.js').Chess;

function validateMove (req, res, next) {
  const { fen, from, to, gameId } = req.body;

  const chess = new Chess(fen);

  try {
    const move = chess.move({ from, to });

    if (move !== null) {
      req.move = { ...move, valid: true };
    } else {
      req.move = { valid: false, fen };
    }
  } catch (e) {
    req.move = { valid: false, fen };
  }

  req.gameId = gameId;

  next();
};

module.exports = validateMove;
