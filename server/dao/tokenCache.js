// TODO: 
class TokenCache {
    constructor() {
        this.cache = {};
    }

    cacheToken(username, password, token) {
        this.cache[username] = { password, token };
    }

    getToken(username) {
        const cachedUser = this.cache[username];
        if (cachedUser) {
            return cachedUser.token;
        }
        return null; // Token not found
    }
}

module.exports = TokenCache;