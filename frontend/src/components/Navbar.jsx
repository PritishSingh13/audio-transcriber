import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Navbar() {
  const navigate = useNavigate();
  const email = localStorage.getItem('email');

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    navigate('/login');
  };

  return (
    <nav
      style={{
        display: 'flex',
        flexWrap: 'wrap',
        gap: '1rem',
        padding: '1rem',
        backgroundColor: '#f0f0f0',
        justifyContent: 'center'
      }}
    >
      <Link to="/">Home</Link>
      <Link to="/upload">Upload</Link>
      {!email && <Link to="/login">Login</Link>}
      {!email && <Link to="/signup">Signup</Link>}
      {email && <span>Welcome, {email}</span>}
      {email && <button onClick={handleLogout}>Logout</button>}
    </nav>
  );
}

export default Navbar;
