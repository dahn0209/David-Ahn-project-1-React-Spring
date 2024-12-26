import React, { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import { LoginResponse } from '../types/Auth';
import styles from '../styles/AuthContext.module.css'; // Import the CSS module

interface AuthState {
  userId: number | null;
  username: string | null;
  role: 'EMPLOYEE' | 'MANAGER' | null;
  isAuthenticated: boolean;
}

interface AuthContextProps {
  authState: AuthState;
  login: (user: LoginResponse) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [authState, setAuthState] = useState<AuthState>({
    userId: null,
    username: null,
    role: null,
    isAuthenticated: false,
  });

  // Rehydrate state from localStorage on initial load
  useEffect(() => {
    const storedAuth = localStorage.getItem('auth');
    if (storedAuth) {
      const parsedAuth = JSON.parse(storedAuth);
      setAuthState({
        userId: parsedAuth.accountId,
        username: parsedAuth.username,
        role: parsedAuth.role,
        isAuthenticated: true,
      });
    }
  }, []);

  const login = (user: LoginResponse) => {
    const userData = {
      accountId: user.accountId,
      username: user.username,
      role: user.role,
    };
    setAuthState({
      userId: user.accountId,
      username: user.username,
      role: user.role,
      isAuthenticated: true,
    });
    localStorage.setItem('auth', JSON.stringify(userData)); // Persist user data in localStorage
  };

  const logout = () => {
    setAuthState({
      userId: null,
      username: null,
      role: null,
      isAuthenticated: false,
    });
    localStorage.removeItem('auth'); // Clear localStorage on logout
  };

  return (
    <AuthContext.Provider value={{ authState, login, logout }}>
      <div className={styles.wrapper}>
        {children}
      </div>
    </AuthContext.Provider>
  );
};

export const useAuthContext = (): AuthContextProps => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuthContext must be used within an AuthProvider');
  }
  return context;
};
