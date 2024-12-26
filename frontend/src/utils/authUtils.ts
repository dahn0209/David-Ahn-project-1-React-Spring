export const saveToken = (token: string): void => {
  localStorage.setItem('authToken', token);
};

export const getToken = (): string | null => {
  return localStorage.getItem('authToken');
};

export const clearToken = (): void => {
  localStorage.removeItem('authToken');
};

export const isAuthenticated = (): boolean => {
  return !!getToken();
};
