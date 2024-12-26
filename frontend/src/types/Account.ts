export type AccountRole = 'EMPLOYEE' | 'MANAGER';

export interface Account {
  accountId: number;
  username: string;
  password?: string; // Optional to exclude password in responses
  role: AccountRole;
}
