import React, { useState } from "react";

const LoginForm = ({ handleLogin }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    handleLogin(username, password);
  };

  const handleFacebookLogin = () => {
    // Perform Facebook login logic
  };

  const handleGoogleLogin = () => {
    // Perform Google login logic
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Username:
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </label>
      <br />
      <label>
        Password:
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </label>
      <br />
      <button type="submit">Login</button>
      <button type="button" className="facebook" onClick={handleFacebookLogin}>
  Login with Facebook
</button>
<button type="button" className="google" onClick={handleGoogleLogin}>
  Login with Google
</button>
    </form>
  );
};

export default LoginForm;
