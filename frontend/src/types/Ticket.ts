export type TicketStatus = 'PENDING' | 'APPROVED' | 'DENIED';

export interface Ticket {
  ticketId: number;
  userId: number;
  amount: number;
  description: string;
  status: TicketStatus;
  submissionDate: string; 
}
