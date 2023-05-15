const express = require('express');
const Chess = require('chess.js').Chess;
const gamesDAO = require('../dao/games');
const usersDAO = require('../dao/users');
const validateMove = require('./middleware/validate_move');

const router = express.Router();

router.post('/move', validateMove, (req, res, next) => {
  const { move, gameId } = req;

  if ( move.valid ) {
    // gamesDAO.updateGame(gameId, { fen: move.after }).then((response) => {
    //   console.log(response);
    //   res.send(move);
    // });
    gamesDAO.updateGame(gameId, { fen: move.after });
    // console.log(move);
    res.send(move);
  } else {
    res.status(400).send(move);
  }
});

router.post('/createGame', (req, res, next) => {
  const { userIdOne, userIdTwo } = req.body;
  const fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

  if(usersDAO.getUser(userIdOne) && usersDAO.getUser(userIdOne)) {
    const gameId = gamesDAO.createGame({userIdOne,userIdTwo,fen}).then((response) => {
      console.log(response);
      res.send(gameId);
    });
  } else {
    res.status(400).send("User not found!");
  }
});

module.exports = router;
