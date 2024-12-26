import axios from 'axios';

export const apiClient = axios.create({
  baseURL: 'http://localhost:8080', // Backend API base URL
  timeout: 5000, // Set a timeout for requests
  
});

