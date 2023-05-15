const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = 3000;

// routes
const chess = require('./routes/chess');
app.use(bodyParser.json());

app.use('/chess', chess);

app.listen(port, () => {
  console.log(`Chess server listening on port ${port}`)
})