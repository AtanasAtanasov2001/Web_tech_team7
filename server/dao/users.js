/**
 * get user
 * @param {string} id - user id
 * @returns {object} - object, containing the userName and password of the user
 */
function getUser(id) {
  // userId
}

/**
 * create user
 * @param {object} data - object, containing the userName and password of the user
 * @returns {string} - user id
 */
function createUser(data) {
  // data.userName
  // data.password
}

/**
 * update user
 * @param {string} id - user id
 * @param {object} data - object, containing the userName and password of the user
 * @returns {string} - user id
 */
function updateUser(id, data) {
  // userId
  // data.userName
  // data.password
}

const usersDAO = { getUser, createUser, updateUser }

module.exports = usersDAO;