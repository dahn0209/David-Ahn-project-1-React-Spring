export interface LoginRequest {
    username: string;
    password: string;
  }
  
  export interface LoginResponse {
    accountId: number;
    username: string;
    role: 'EMPLOYEE' | 'MANAGER';
    token: string; // JWT or session token
  }
  
  export interface RegisterRequest {
    username: string;
    password: string;
  }
  