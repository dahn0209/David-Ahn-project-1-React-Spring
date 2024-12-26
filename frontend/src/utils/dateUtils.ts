import { format } from 'date-fns';

export const formatDate = (dateString: string): string => {
  const date = new Date(dateString);
  return format(date, 'yyyy-MM-dd HH:mm:ss'); // Format: YYYY-MM-DD HH:mm:ss
};

export const getCurrentTimestamp = (): string => {
  return new Date().toISOString();
};
