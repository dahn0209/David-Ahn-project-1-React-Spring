import React, { useEffect, useState } from 'react';
import { getAllTickets, updateTicket } from '../api/ticketApi';

const ManagerDashboard: React.FC = () => {
  const [tickets, setTickets] = useState([]);
  const [updatingTicketId, setUpdatingTicketId] = useState<number | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchTickets = async () => {
      try {
        const ticketData = await getAllTickets();
        setTickets(ticketData);
        console.log('Fetched tickets:', ticketData);
      } catch (error) {
        console.error('Error fetching tickets:', error);
      }
    };

    fetchTickets();
  }, []);

  const handleUpdateStatus = async (ticketId: number, newStatus: string) => {
    setError(null);
    setUpdatingTicketId(ticketId); // Show spinner or disable button
    try {
      const updatedTicket = await updateTicket(ticketId, { status: newStatus });
      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket.ticketId === ticketId ? { ...ticket, status: updatedTicket.status } : ticket
        )
      );
      console.log('Updated ticket:', updatedTicket);
    } catch (err) {
      setError('Failed to update ticket. Please try again.');
      console.error('Error updating ticket:', err);
    } finally {
      setUpdatingTicketId(null); // Re-enable button
    }
  };

  return (
    <div>
      <h1>Manager Dashboard</h1>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <ul>
        {tickets.map((ticket) => (
          <li key={ticket.ticketId}>
            {ticket.description} - {ticket.status} (${ticket.amount})
            {ticket.status === 'PENDING' && (
              <>
                <button
                  onClick={() => handleUpdateStatus(ticket.ticketId, 'APPROVED')}
                  disabled={updatingTicketId === ticket.ticketId}
                >
                  {updatingTicketId === ticket.ticketId ? 'Updating...' : 'Approve'}
                </button>
                <button
                  onClick={() => handleUpdateStatus(ticket.ticketId, 'DENIED')}
                  disabled={updatingTicketId === ticket.ticketId}
                >
                  {updatingTicketId === ticket.ticketId ? 'Updating...' : 'Deny'}
                </button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ManagerDashboard;
