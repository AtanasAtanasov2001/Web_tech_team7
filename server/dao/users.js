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
async function createUser(data) {
	const url = 'http://localhost:8080/registration/skinny';

	const requestOptions = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			username: data.username,
			password: data.password
		})
	};

	return await fetch(url, requestOptions)
	.then(response => response.json())
	.then(data => data.toString());
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