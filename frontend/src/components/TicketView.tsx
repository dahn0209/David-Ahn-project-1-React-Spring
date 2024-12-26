import React, { useEffect, useState } from 'react';
import { getTicketById } from '../api/ticketApi';
import { Ticket } from '../types/Ticket';

interface TicketViewProps {
  ticketId: number; // ID of the ticket to display
}

const TicketView: React.FC<TicketViewProps> = ({ ticketId }) => {
  const [ticket, setTicket] = useState<Ticket | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchTicket = async () => {
      try {
        setLoading(true);
        const ticketData = await getTicketById(ticketId);
        setTicket(ticketData);
      } catch (err) {
        setError('Failed to fetch ticket details');
      } finally {
        setLoading(false);
      }
    };

    fetchTicket();
  }, [ticketId]);

  if (loading) return <p>Loading ticket details...</p>;
  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  if (!ticket) return <p>No ticket found.</p>;

  return (
    <div>
      <h2>Ticket Details</h2>
      <p><strong>ID:</strong> {ticket.ticketId}</p>
      <p><strong>Submitted By:</strong> {ticket.userId}</p>
      <p><strong>Amount:</strong> ${ticket.amount.toFixed(2)}</p>
      <p><strong>Description:</strong> {ticket.description}</p>
      <p><strong>Status:</strong> {ticket.status}</p>
      <p><strong>Submitted On:</strong> {new Date(ticket.submissionDate).toLocaleString()}</p>
    </div>
  );
};

export default TicketView;

