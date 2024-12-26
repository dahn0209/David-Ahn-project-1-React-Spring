import React, { useState } from 'react';
import { createTicket } from '../api/ticketApi';
import { useAuthContext } from '../context/AuthContext';
import { useTicketContext } from '../context/TicketContext';

const TicketForm: React.FC = () => {
  const { authState } = useAuthContext();
  const { addTicket } = useTicketContext(); // Access addTicket from the context
  const [amount, setAmount] = useState(0);
  const [description, setDescription] = useState('');
  const [success, setSuccess] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const newTicket = await createTicket({
        userId: authState.userId!,
        amount,
        description,
      });
      addTicket(newTicket); // Add the new ticket to the context
      setSuccess('Ticket submitted successfully!');
    } catch (err) {
      console.error('Error submitting ticket:', err);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Submit a Ticket</h2>
      {success && <p style={{ color: 'green' }}>{success}</p>}
      <div>
        <label>Amount:</label>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(Number(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Description:</label>
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        ></textarea>
      </div>
      <button type="submit">Submit</button>
    </form>
  );
};

export default TicketForm;
