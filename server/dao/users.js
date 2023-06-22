const axios = require('axios');
// const TokenCache = require('./TokenCache');
// const tokenCache = new TokenCache();
// TODO: tokenCache should not be init here
// TODO: password should be coded

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
 * get user token
 * @param {object} data - object, containing the userName and password of the user
 * @returns {promise} - eg. { "token": "..." }
 */
function getToken(data) {
    const {username, password} = data;
    const url = `http://localhost:8080/registration/customer-login/skinny`;

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
    const url = 'http://localhost:8080/registration/skinny';

    let config = {
        headers: {
          'Content-Type': 'application/json'
        }
    }

    let body = { username, password };

    return axios.post(url, body, config)
        .then(res => {return {userId: res.data} } )
        .catch(e => {
            console.error(`ERROR: Username taken!`)
            throw new Error(`Username taken!`);
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

const usersDAO = {getUser, createUser, getToken}

module.exports = usersDAO;