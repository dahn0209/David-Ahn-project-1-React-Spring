// import React, { useState } from 'react';
// import { createTicket } from '../api/ticketApi';
// import { useAuthContext } from '../context/AuthContext';
// import { useTicketContext } from '../context/TicketContext';

// const TicketForm: React.FC = () => {
//   const { authState } = useAuthContext();
//   const { addTicket } = useTicketContext();
//   const [amount, setAmount] = useState<string>('');
//   const [description, setDescription] = useState('');
//   const [success, setSuccess] = useState('');

//   const handleSubmit = async (e: React.FormEvent) => {
//     e.preventDefault();
//     const numericAmount = parseFloat(amount); 
//     if (isNaN(numericAmount)) {
//       console.error('Invalid amount');
//       return;
//     }

//     try {
//       const newTicket = await createTicket({
//         userId: authState.userId!,
//         amount: numericAmount,
//         description,
//       });
//       addTicket(newTicket);
//       setSuccess('Ticket submitted successfully!');
//       setAmount(''); 
//       setDescription('');
//     } catch (err) {
//       console.error('Error submitting ticket:', err);
//     }
//   };

//   return (
//     <form onSubmit={handleSubmit}>
//       <h2>Submit a Ticket</h2>
//       {success && <p style={{ color: 'green' }}>{success}</p>}
//       <div>
//         <label>Amount:</label>
//         <input
//           type="text"
//           value={amount}
//           onChange={(e) => setAmount(e.target.value)} 
//           min="0" 
//           step="0.01" 
//         />
//       </div>
//       <div>
//         <label>Description:</label>
//         <textarea
//           value={description}
//           onChange={(e) => setDescription(e.target.value)}
//           required
//         ></textarea>
//       </div>
//       <button type="submit">Submit</button>
//     </form>
//   );
// };

// export default TicketForm;

import React, { useState } from 'react';
import { createTicket } from '../api/ticketApi';
import { useAuthContext } from '../context/AuthContext';
import { useTicketContext } from '../context/TicketContext';

const TicketForm: React.FC = () => {
  const { authState } = useAuthContext();
  const { addTicket } = useTicketContext();
  const [amount, setAmount] = useState<string>('');
  const [description, setDescription] = useState('');
  const [success, setSuccess] = useState('');

  const handleAmountChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;

    // Allow empty string to enable deletion, else validate and format
    if (value === '' || /^\d*\.?\d*$/.test(value)) {
      setAmount(value);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const numericAmount = parseFloat(amount);
    if (isNaN(numericAmount)) {
      console.error('Invalid amount');
      return;
    }

    try {
      const formattedAmount = parseFloat(numericAmount.toFixed(2)); // Ensure two decimal places
      const newTicket = await createTicket({
        userId: authState.userId!,
        amount: formattedAmount,
        description,
      });
      addTicket(newTicket);
      setSuccess('Ticket submitted successfully!');
      setAmount('');
      setDescription('');
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
          type="text"
          value={amount}
          onChange={handleAmountChange}
          required
          placeholder="Enter amount"
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


