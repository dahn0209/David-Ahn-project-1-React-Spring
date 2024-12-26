import React, { useEffect } from 'react';
import TicketForm from '../components/TicketForm';
import TicketList from '../components/TicketList';
import { useAuthContext } from '../context/AuthContext';
import { useTicketContext } from '../context/TicketContext';
import { getAllTicketsForUser } from '../api/ticketApi';

const EmployeeDashboard: React.FC = () => {
  const { authState } = useAuthContext();
  const { setTickets } = useTicketContext();

  useEffect(() => {
    const fetchTickets = async () => {
      try {
        const tickets = await getAllTicketsForUser(authState.userId!);
        setTickets(tickets); // Update TicketContext with fetched tickets
      } catch (error) {
        console.error("Error fetching tickets:", error);
      }
    };

    if (authState.userId) {
      fetchTickets();
    }
  }, [authState.userId, setTickets]);

  return (
    <div>
      <h1>Employee Dashboard</h1>
      <p>Welcome, {authState.username}</p>
      <TicketForm />
      <TicketList />
    </div>
  );
};

export default EmployeeDashboard;
