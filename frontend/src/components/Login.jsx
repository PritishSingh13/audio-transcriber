import React, { useState } from 'react';
import axios from 'axios';

function Login() {
  const [user, setUser] = useState({ email: '', password: '' });
  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8080/api/users/login', user);
      const { token } = res.data;

      localStorage.setItem('token', token);
      localStorage.setItem('email', user.email);

      setMessage('Login successful');
    } catch (err) {
      setMessage('Login failed');
    }
  };

  return (
    <div style={{ padding: '1rem' }}>
      <h2 className="form-heading">Login</h2>
      <form onSubmit={handleSubmit} style={{ maxWidth: '400px', margin: '0 auto' }}>
        <input type="email" name="email" placeholder="Email" onChange={handleChange} required />
        <input type="password" name="password" placeholder="Password" onChange={handleChange} required />
        <button type="submit">Login</button>
      </form>
      <p>{message}</p>
    </div>
  );
}

export default Login;
