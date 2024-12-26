export type TicketStatus = 'PENDING' | 'APPROVED' | 'DENIED';

export interface Ticket {
  ticketId: number;
  userId: number;
  amount: number;
  description: string;
  status: TicketStatus;
  submissionDate: string; // ISO 8601 formatted date
}
