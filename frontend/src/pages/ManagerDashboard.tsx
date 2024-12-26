import React, { useEffect, useState } from 'react';
import { getAllTickets, updateTicket } from '../api/ticketApi';
import styles from '../styles/ManagerDashboard.module.css';

const ManagerDashboard: React.FC = () => {
  const [tickets, setTickets] = useState([]);
  const [updatingTicketId, setUpdatingTicketId] = useState<number | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchTickets = async () => {
      try {
        const ticketData = await getAllTickets();
        setTickets(ticketData);
      } catch (error) {
        setError('Failed to fetch tickets.');
      }
    };

    fetchTickets();
  }, []);

  const handleUpdateStatus = async (ticketId: number, newStatus: string) => {
    setError(null);
    setUpdatingTicketId(ticketId);
    try {
      const updatedTicket = await updateTicket(ticketId, { status: newStatus });
      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket.ticketId === ticketId ? { ...ticket, status: updatedTicket.status } : ticket
        )
      );
    } catch (err) {
      setError('Failed to update ticket. Please try again.');
    } finally {
      setUpdatingTicketId(null);
    }
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.heading}>Manager Dashboard</h1>
      {error && <p className={styles.error}>{error}</p>}
      <ul className={styles.list}>
        {tickets.map((ticket) => (
          <li key={ticket.ticketId} className={styles.listItem}>
            <div className={styles.ticketDetails}>
              <span className={`${styles.description}`}>{ticket.description}</span>
              <span className={`${styles.status}`}>{ticket.status}</span>
              <span className={`${styles.amount}`}>${ticket.amount.toFixed(2)}</span>
            </div>
            {ticket.status === 'PENDING' && (
              <div className={styles.buttons}>
                <button
                  className={`${styles.button} ${styles.approve}`}
                  onClick={() => handleUpdateStatus(ticket.ticketId, 'APPROVED')}
                  disabled={updatingTicketId === ticket.ticketId}
                >
                  {updatingTicketId === ticket.ticketId ? 'Updating...' : 'Approve'}
                </button>
                <button
                  className={`${styles.button} ${styles.deny}`}
                  onClick={() => handleUpdateStatus(ticket.ticketId, 'DENIED')}
                  disabled={updatingTicketId === ticket.ticketId}
                >
                  {updatingTicketId === ticket.ticketId ? 'Updating...' : 'Deny'}
                </button>
              </div>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ManagerDashboard;
