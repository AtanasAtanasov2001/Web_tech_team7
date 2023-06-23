const axios = require('axios');
const path = require("path");
require('dotenv').config({ path: path.resolve(__dirname, '../../.env') });
const DB_SERVER = process.env.ENV === 'prod' ? process.env.DB_SERVER : 'localhost';


// TODO: password should be coded

/**
 * get user
 * @param {string} id - user id
 * @returns {promise} - eg. { "id": 14, "username": "...", "email": "...", "registrationDate": "...", "customerDetails": {
    customerId: 14,
    name: "...",
    lastName: "...",
    birthDate: "...",
    city: "..."
  }
}
 */
function getUser(id) {
    const url = `http://${DB_SERVER}:8080/customer/by-id/${id}`;

    return axios.get(url)
        .then(res => res.data)
        .catch(e => {
            console.error(`ERROR: User id ${id} not found!`)
            throw new Error(`User id ${id} not found!`);
        });
}

function getUserByToken(token) {
    const url = `http://localhost:8080/customer/token`;

    let body = { token };

    let config = {
        headers: {
          'Content-Type': 'application/json',
          'Authorization' : token
        }
      }

    return axios.post(url, body, config)
        .then(res => res.data)
        .catch(e => {
            console.error(`ERROR: `)
            throw new Error(`err`);
        });
}

function getUserByUsername(username) {
    const url = `http://localhost:8080/registration/${username}`;

    return axios.get(url)
        .then(res => res.data)
        .catch(e => {
            console.error(`ERROR: `)
            throw new Error(`err`);
        });
}

/**
 * get user token
 * @param {object} data - object, containing the userName and password of the user
 * @returns {promise} - eg. { "token": "..." }
 */
function getToken(data) {
    const {username, password} = data;
    const url = `http://${DB_SERVER}:8080/registration/customer-login/skinny`;

    //TODO: Put credentials in body not query
    let config = {
        params: { username, password }
    };

    return axios.post(url, null, config)
        .then(res => res.data)
        .catch(e => {
            console.error(`ERROR: Wrong username and password!`)
            throw new Error(`Wrong username and password!`);
        });
}

/**
 * create user
 * @param {object} data - object, containing the userName and password of the user
 * @returns {promise} - eg. { "userId": 11 }
}
 */
async function createUser(data) {
    const {username, password} = data;
    const url = `http://${DB_SERVER}:8080/registration/skinny`;

    let config = {
        headers: {
          'Content-Type': 'application/json'
        }
    }

    let body = { username, password };

    return axios.post(url, body, config)
        .then(res => {return {userId: res.data} } )
        .catch(e => {
            console.error(`ERROR: Username taken! ${e}`)
            throw new Error(`Username taken! DB_SERVER: ${DB_SERVER} ENV: ${ENV} url: ${url}`);
        });
}

// async function login(username, password) {
//     let token = tokenCache.getToken(username);

//     if (!token) {
//         try {
//             const url = `http://localhost:8080/registration/customer-login/skinny`;
//             const response = await axios.post(url, null, {
//                 params: {
//                     username,
//                     password
//                 }
//             });
//             token = response.data;
//             tokenCache.cacheToken(username, password, token);
//         } catch (error) {
//             console.error("Invalid credentials");
//             throw new Error("Invalid credentials");
//         }
//     }

//     return token;
// }

const usersDAO = {getUser, createUser, getToken, getUserByToken, getUserByUsername}

module.exports = usersDAO;