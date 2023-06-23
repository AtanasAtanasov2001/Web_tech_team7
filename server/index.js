const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = 4000;
const cors = require('cors')
require('dotenv').config();

const http = require('http');
const socketIO = require('socket.io');

const server = http.createServer(app);
// const io = socketIO(server);
// const cors = require('cors');

// app.use((req, res, next) => {
//   res.setHeader('Access-Control-Allow-Origin', '*');
//   res.setHeader('Access-Control-Allow-Methods', 'GET, POST');
//   res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
//   next();
// });
// app.use(cors());
// const io = require("socket.io")(server, {
//   cors: {
//     origin: "http://localhost:3000",
//     methods: ["GET", "POST"]
//   }
// });

// io.on('connection', (socket) => {
//   console.log('A user connected.');

//   socket.on('chat message', (message) => {
//     console.log('Received message:', message);
//     io.emit('chat message', message); // Broadcast the message to all connected clients
//   });

//   socket.on('disconnect', () => {
//     console.log('A user disconnected.');
//   });
// });

// routes
const chess = require('./routes/chess');
const auth = require('./routes/auth');

app.use(bodyParser.json());
app.use(cors());
app.use('/chess', chess);
app.use('/auth', auth);


server.listen(port, () => {
  console.log(`Chess server listening on port ${port}`)
})