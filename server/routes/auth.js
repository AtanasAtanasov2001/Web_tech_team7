const express = require('express');
const usersDAO = require('../dao/users');
const axios = require('axios');

const router = express.Router();

router.post('/mockLogin', (req, res, next) => {
    const {username, password} = req.body;

    if (username == "111" && password == "111") {
        res.json({token: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib3FuIiwiaWF0IjoxNjg3NDM1NDAzLCJleHAiOjE2ODc0NzE0MDN9.OH99fUtmCYphce5lZvIHgFouKOfy8lg0Jq_Q61vYkDI"});
    } else {
        res.status(400).send("err log in");
    }
});

router.post('/mockRegister', (req, res, next) => {
    const {username, password} = req.body;
    console.log("into the mock reg")
    if (username == "111" && password == "111") {
        res.json({userId: 11});
    } else {
        res.status(400).send("err log in");
    }
});

router.post("/register", (req, res) => {
    const {username, password} = req.body;

    if(username && password) {
        usersDAO.createUser({username, password})
            .then(userId => res.send(userId))
            .catch(e => res.status(500).send(`${e}`));
    } else {
        res.status(400).send("No username and password!");
    }
});

router.post("/login", (req, res) => {
    const {username, password} = req.body;

    if(username && password) {
        usersDAO.getToken({username, password})
            .then(token => res.send(token))
            .catch(e => res.status(500).send(`${e}`));
    } else {
        res.status(400).send("No username and password!");
    }
});

module.exports = router;
