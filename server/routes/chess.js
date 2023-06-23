const express = require('express');
const gamesDAO = require('../dao/games');
const usersDAO = require('../dao/users');
const validateMove = require('./middleware/validate_move');
const authMiddleware = require('./middleware/auth');

const router = express.Router();

router.get('/game/:gameId', (req, res, next) => {
    const gameId = req.params.gameId;

    if (gameId) {
        gamesDAO.getGameState(gameId)
            .then(state => res.send(state))
            .catch(e => res.status(500).send(`${e}`));
    } else {
        res.status(400).send("No Game Id");
    }
});

router.post('/move', authMiddleware, validateMove, (req, res, next) => {
    // TODO: check if the correct user is moving
    const { move, gameId, token } = req;

    if (move.valid) {
        gamesDAO.getGameState(gameId)
            .then(() => gamesDAO.updateGame(gameId, { fen: move.after, token }))
            .then(() => res.send(move))
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
            .then(() => usersDAO.getUser(userIdTwo))
            .then(() => gamesDAO.createGame({ userIdOne, userIdTwo, fen, token }))
            .then(gameId => res.send(gameId))
            .catch(e => res.status(500).send(`${e}`));
    } else {
        res.status(400).send("No userIdOne and userIdTwo!");
    }
});

module.exports = router;
