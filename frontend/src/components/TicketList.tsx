import React from 'react';
import { useTicketContext } from '../context/TicketContext';
import styles from '../styles/TicketList.module.css';

const TicketList: React.FC = () => {
  const { tickets } = useTicketContext();

  return (
    <div className={styles.container}>
      <h2 className={styles.heading}>Your Tickets</h2>
      {tickets.length === 0 ? (
        <p className={styles.message}>No tickets available.</p>
      ) : (
        <ul className={styles.list}>
          {tickets.map((ticket) => (
            <li className={styles.listItem} key={ticket.ticketId}>
              <span className={styles.description}>{ticket.description}</span>
              <span className={styles.status}>{ticket.status}</span>
              <span className={styles.amount}>${ticket.amount.toFixed(2)}</span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default TicketList;
