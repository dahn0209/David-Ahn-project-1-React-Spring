import React from 'react';
import { useTicketContext } from '../context/TicketContext';

const TicketList: React.FC = () => {
  const { tickets } = useTicketContext();

  return (
    <div>
      <h2>Your Tickets</h2>
      {tickets.length === 0 ? (
        <p>No tickets available.</p>
      ) : (
        <ul>
          {tickets.map((ticket) => (
            <li key={ticket.ticketId}>
              {ticket.description} - {ticket.status} (${ticket.amount})
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default TicketList;
