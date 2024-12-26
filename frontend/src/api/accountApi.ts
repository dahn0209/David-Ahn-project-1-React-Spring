import axios from 'axios';
import { LoginRequest, LoginResponse, RegisterRequest, } from '../types/Auth';
import { Account } from '../types/Account';
import {apiClient}  from '../utils/apiUtils';

const API_BASE_URL = '/accounts'; // Base path for account-related APIs

export const login = async (credentials: LoginRequest): Promise<LoginResponse> => {
  const response = await apiClient.post(`${API_BASE_URL}/login`, credentials);
  return response.data;
};

export const register = async (details: RegisterRequest): Promise<Account> => {
  const response = await apiClient.post(`${API_BASE_URL}/register`, details);
  return response.data;
};

export const getAllAccounts = async (): Promise<Account[]> => {
  const response = await apiClient.get(API_BASE_URL);
  return response.data;
};
