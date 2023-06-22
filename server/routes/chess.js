const express = require('express');
const Chess = require('chess.js').Chess;
const gamesDAO = require('../dao/games');
const usersDAO = require('../dao/users');
const validateMove = require('./middleware/validate_move');

const router = express.Router();

router.get('/test', validateMove, (req, res, next) => {
    const token = req.header('Authorization')

    gamesDAO.getGame('5b4fed54-a1d7-4e5f-aa6d-71a9a1174abe', token)
        .then(r => res.send(r))
        .catch(e => res.status(500).send(`${e}`));
});

router.post('/move', validateMove, (req, res, next) => {
    //TODO: no auth does not throw err
    const {move, gameId} = req;
    const token = req.header('Authorization')
    if (move.valid) {
        gamesDAO.getGame(gameId)
            .then(gamesDAO.updateGame(gameId, {fen: move.after, token}))
            .then(r => res.send(move))
            .catch(e => res.status(500).send(`${e}`));
    } else {
        res.status(400).send(move);
    }
});

router.post('/createGame', (req, res, next) => {
    const {userIdOne, userIdTwo} = req.body;
    const fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    const token = req.header('Authorization');
    usersDAO.getUser(userIdOne)
        .then(u1 => usersDAO.getUser(userIdTwo))
        .then(u2 => gamesDAO.createGame({userIdOne, userIdTwo, fen, token}))
        .then(gameId => res.send(gameId))
        .catch(e => res.status(500).send(`${e}`));
});

router.post('/register', (req, res) => {
  


})

module.exports = router;
