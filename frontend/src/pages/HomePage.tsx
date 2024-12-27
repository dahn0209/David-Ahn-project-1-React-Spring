import React from 'react';
import { useAuthContext } from '../context/AuthContext';
import { Link } from 'react-router-dom';

const HomePage: React.FC = () => {
  
  const { authState } = useAuthContext();

  return (
    <div>
      <h1>Welcome to the Ticket System</h1>
      {!authState.isAuthenticated ? (
        <div>
          <p>Please log in to access your dashboard:</p>
          <Link to="/login">Login</Link> | <Link to="/register">Register</Link>
        </div>
      ) : authState.role === 'EMPLOYEE' ? (
        <Link to="/employee-dashboard">Go to Employee Dashboard</Link>
      ) : (
        <Link to="/manager-dashboard">Go to Manager Dashboard</Link>
      )}
    </div>
  );
};

export default HomePage;
