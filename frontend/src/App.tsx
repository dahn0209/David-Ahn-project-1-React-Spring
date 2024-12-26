import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import EmployeeDashboard from './pages/EmployeeDashboard';
import ManagerDashboard from './pages/ManagerDashboard';
// import TicketDetailPage from './pages/TicketDetailPage';
import NotFoundPage from './pages/NotFoundPage';
import Navbar from './components/NavBar';
import Footer from './components/Footer';
import { AuthProvider } from './context/AuthContext';
import { TicketProvider } from './context/TicketContext';

const App: React.FC = () => {
  return (
    <AuthProvider>
      <TicketProvider>
        <Router>
          <Navbar />
          <main className="container">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/employee-dashboard" element={<EmployeeDashboard />} />
            <Route path="/manager-dashboard" element={<ManagerDashboard />} />
            <Route path="*" element={<NotFoundPage />} />
            </Routes>
          </main>
          <Footer />
        </Router>
      </TicketProvider>
    </AuthProvider>
  );
};

export default App;
