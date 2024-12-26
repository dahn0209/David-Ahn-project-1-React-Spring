import  {apiClient}  from '../utils/apiUtils';
import { Ticket} from '../types/Ticket';


const API_BASE_URL = '/tickets'; // Base path for ticket-related APIs

export const getAllTickets = async (): Promise<Ticket[]> => {
  const response = await apiClient.get(API_BASE_URL);
  return response.data;
};

export const getAllTicketsForUser = async (userId: number): Promise<Ticket[]> => {
  const response = await apiClient.get(`${API_BASE_URL}/user/${userId}`);
  return response.data;
};

export const getTicketById = async (ticketId: number): Promise<Ticket> => {
  const response = await apiClient.get(`${API_BASE_URL}/${ticketId}`);
  return response.data;
};

export const createTicket = async (ticket: Omit<Ticket, 'ticketId' | 'status' | 'submissionDate'>): Promise<Ticket> => {
  const response = await apiClient.post(API_BASE_URL, ticket);
  return response.data;
};


export const updateTicket = async (ticketId: number, updatedTicket: { status: string }) => {
  const response = await apiClient.put(`${API_BASE_URL}/${ticketId}`, updatedTicket); // Send the ticket as body
  return response.data;
};

