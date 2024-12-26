// import { useState, useEffect } from 'react';
// import { getAllTickets, createTicket, 
//   // updateTicketStatus, 
//   updateTicket } from '../api/ticketApi';
// import { Ticket, TicketStatus } from '../types/Ticket';

// const useTickets = (userId?: number) => {
//   const [tickets, setTickets] = useState<Ticket[]>([]);
//   const [loading, setLoading] = useState<boolean>(false);
//   const [error, setError] = useState<string | null>(null);

//   const fetchTickets = async () => {
//     setLoading(true);
//     try {
//       const ticketData = userId ? await getAllTickets() : await getAllTickets();
//       setTickets(ticketData);
//     } catch (err) {
//       setError('Failed to fetch tickets');
//     } finally {
//       setLoading(false);
//     }
//   };

//   const addTicket = async (newTicket: Omit<Ticket, 'ticketId' | 'status' | 'submissionDate'>) => {
//     try {
//       const createdTicket = await createTicket(newTicket);
//       setTickets((prevTickets) => [...prevTickets, createdTicket]);
//     } catch (err) {
//       setError('Failed to create ticket');
//     }
//   };

//   const updateTicket = async (ticketId: number, status: TicketStatus) => {
//     try {
//       await updateTicket(ticketId, status); // Replace `1` with current user ID
//       setTickets((prevTickets) =>
//         prevTickets.map((ticket) =>
//           ticket.ticketId === ticketId ? { ...ticket, status } : ticket
//         )
//       );
//     } catch (err) {
//       setError('Failed to update ticket status');
//     }
//   };

//   useEffect(() => {
//     fetchTickets();
//   }, [userId]);

//   return { tickets, loading, error, addTicket, updateTicket };
// };

// export default useTickets;
export {}