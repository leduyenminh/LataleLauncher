import React, { useState } from "react";

function LoginForm({ onLogin }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      await onLogin({ username, password });
    } catch (err) {
      setError("Invalid username or password");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form className="login-form" onSubmit={handleLogin}>
      <h2>MMORPG Login</h2>

      {error && <p className="error">{error}</p>}

      <label>Username</label>
      <input value={username} onChange={(e) => setUsername(e.target.value)} />

      <label>Password</label>
      <input type="password"
             value={password}
             onChange={(e) => setPassword(e.target.value)} />

      <button disabled={loading}>
        {loading ? "Logging in..." : "Login"}
      </button>
    </form>
  );
}

export default LoginForm;
