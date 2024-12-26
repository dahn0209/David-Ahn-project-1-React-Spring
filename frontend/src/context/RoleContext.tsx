// import React, { createContext, useContext, ReactNode } from 'react';
// import { useAuthContext } from './AuthContext';

// interface RoleContextType {
//   isManager: boolean;
//   isEmployee: boolean;
// }

// const RoleContext = createContext<RoleContextType | undefined>(undefined);

// export const RoleProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
//   const { authState } = useAuthContext();

//   const isManager = authState.role === 'MANAGER';
//   const isEmployee = authState.role === 'EMPLOYEE';

//   return (
//     <RoleContext.Provider value={{ isManager, isEmployee }}>
//       {children}
//     </RoleContext.Provider>
//   );
// };

// export const useRoleContext = (): RoleContextType => {
//   const context = useContext(RoleContext);
//   if (!context) {
//     throw new Error('useRoleContext must be used within a RoleProvider');
//   }
//   return context;
// };
export {}