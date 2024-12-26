// import { Ticket, TicketStatus } from './Ticket';
// import { LoginResponse } from './Auth';

// /**
//  * Authentication Context Type
//  * Defines the shape of the global authentication context.
//  */
// export interface AuthContextType {
//   userId: number | null; // The ID of the logged-in user
//   username: string | null; // The username of the logged-in user
//   role: 'EMPLOYEE' | 'MANAGER' | null; // User's role
//   isAuthenticated: boolean; // Whether the user is logged in
//   login: (user: LoginResponse) => void; // Function to log the user in
//   logout: () => void; // Function to log the user out
// }

// /**
//  * Ticket Context Type
//  * Defines the shape of the global ticket management context.
//  */
// export interface TicketContextType {
//   tickets: Ticket[]; // List of tickets managed globally
//   setTickets: React.Dispatch<React.SetStateAction<Ticket[]>>; // Function to update tickets
//   addTicket: (ticket: Ticket) => void; // Function to add a new ticket
//   // updateTicketStatus: (ticketId: number, status: TicketStatus) => void; // Function to update a ticket's status
// }
export {}