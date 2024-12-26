import axios from 'axios';
import { getToken } from './authUtils';

export const apiClient = axios.create({
  baseURL: 'http://localhost:8080', // Backend API base URL
  timeout: 5000, // Set a timeout for requests
  
});

// apiClient.interceptors.request.use(
//   (config) => {
//     const token = getToken();
//     if (token) {
//       config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
//   },
//   (error) => {
//     console.error('API Request Error:', error);
//     return Promise.reject(error);
//   }
// );

// apiClient.interceptors.response.use(
//   (response) => response,
//   (error) => {
//     console.error('API Response Error:', error.response || error);
//     return Promise.reject(error);
//   }
// );

// export const handleApiError = (error: any): string => {
//   if (error.response) {
//     return error.response.data?.message || 'An error occurred';
//   }
//   return 'Network error';
// };
