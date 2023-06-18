const axios = require('axios');

/**
 * get user
 * @param {string} id - user id
 * @returns {object} - object, containing the userName and password of the user
 */
function getUser(id) {
	const url = `http://localhost:8080/customer/${id}`;

	return axios.get(url)
		.then(res => res.data)
		.catch(e => {
			console.error(`ERROR: User id ${id} not found!`)
			throw new Error(`User id ${id} not found!`);
		});
}

/**
 * create user
 * @param {object} data - object, containing the userName and password of the user
 * @returns {string} - user id
 */
async function createUser(data) {
	// TODO: wrong
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
	// TODO:
}

const usersDAO = { getUser, createUser, updateUser }

module.exports = usersDAO;