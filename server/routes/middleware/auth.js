
function authMiddleware (req, res, next) {
  const token = req.header('Authorization');

  if (token) {
    req.token = token;
    next();
  } else {
    console.error('ERROR: No Authorization token found in req header!')
    res.status(400).send("No Authorization token found in req header!");
  }
};

module.exports = authMiddleware;
