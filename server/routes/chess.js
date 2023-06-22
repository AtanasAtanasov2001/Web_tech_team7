const express = require('express');
const gamesDAO = require('../dao/games');
const usersDAO = require('../dao/users');
const validateMove = require('./middleware/validate_move');
const authMiddleware = require('./middleware/auth');

const router = express.Router();

router.post('/move', authMiddleware, validateMove, (req, res, next) => {
    const { move, gameId, token } = req;

    if (move.valid) {
        gamesDAO.getGame(gameId)
            .then(gamesDAO.updateGame(gameId, {fen: move.after, token}))
            .then(r => res.send(move))
            .catch(e => res.status(500).send(`${e}`));
    } else {
        res.status(400).send(move);
    }
});

router.post('/createGame', authMiddleware, (req, res, next) => {
    const { userIdOne, userIdTwo } = req.body;
    const { token } = req;
    const fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    if (userIdOne && userIdTwo) {
        usersDAO.getUser(userIdOne)
        .then(u1 => usersDAO.getUser(userIdTwo))
        .then(u2 => gamesDAO.createGame({userIdOne, userIdTwo, fen, token}))
        .then(gameId => res.send(gameId))
        .catch(e => res.status(500).send(`${e}`));
    } else {
        res.status(400).send("No userIdOne and userIdTwo!");
    }
});

module.exports = router;
