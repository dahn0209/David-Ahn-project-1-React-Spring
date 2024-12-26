import React, { createContext, useContext, useState, ReactNode, useEffect } from 'react';
import { Ticket } from '../types/Ticket';

interface TicketContextType {
  tickets: Ticket[];
  setTickets: React.Dispatch<React.SetStateAction<Ticket[]>>;
  addTicket: (ticket: Ticket) => void;
}

const TicketContext = createContext<TicketContextType | undefined>(undefined);

export const TicketProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [tickets, setTickets] = useState<Ticket[]>([]);

  const addTicket = (newTicket: Ticket) => {
    setTickets((prevTickets) => [...prevTickets, newTicket]);
  };

  useEffect(() => {
  }, [tickets]);

  return (
    <TicketContext.Provider value={{ tickets, setTickets, addTicket }}>
      {children}
    </TicketContext.Provider>
  );
};

export const useTicketContext = (): TicketContextType => {
  const context = useContext(TicketContext);
  if (!context) {
    throw new Error('useTicketContext must be used within a TicketProvider');
  }
  return context;
};
