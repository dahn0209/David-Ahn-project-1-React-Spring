import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuthContext } from '../context/AuthContext';
import styles from '../styles/Navbar.module.css'; // Import the CSS module

const Navbar: React.FC = () => {
  const { authState, logout } = useAuthContext();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout(); // Clear authentication state
    navigate('/'); // Redirect to Home
  };

  return (
    <nav className={styles.navbar}>
      <h1 className={styles.title}>Reimbursement App</h1>
      <ul className={styles.navList}>
        <li className={styles.navItem}>
          <Link to="/" className={styles.navLink}>
            Home
          </Link>
        </li>
        {!authState.isAuthenticated ? (
          <>
            <li className={styles.navItem}>
              <Link to="/login" className={styles.navLink}>
                Login
              </Link>
            </li>
            <li className={styles.navItem}>
              <Link to="/register" className={styles.navLink}>
                Register
              </Link>
            </li>
          </>
        ) : (
          <>
            {authState.role === 'EMPLOYEE' && (
              <li className={styles.navItem}>
                <Link to="/employee-dashboard" className={styles.navLink}>
                  Employee Dashboard
                </Link>
              </li>
            )}
            {authState.role === 'MANAGER' && (
              <li className={styles.navItem}>
                <Link to="/manager-dashboard" className={styles.navLink}>
                  Manager Dashboard
                </Link>
              </li>
            )}
            <li className={styles.navItem}>
              <button onClick={handleLogout} className={styles.logoutButton}>
                Logout
              </button>
            </li>
          </>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
