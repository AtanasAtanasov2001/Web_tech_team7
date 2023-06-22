const express = require('express');
const usersDAO = require('../dao/users');

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

    if (username == "111" && password == "111") {
        res.send("success");
    } else {
        res.status(400).send("err log in");
    }
});

// router.post("/login", (req, res) => {
//     const {username, password} = req.body;
//     usersDAO.login(username, password)
//         .then(token => res.send(token))
//         .catch((e => res.status(500).send(`${e}`)));
// });

module.exports = router;
