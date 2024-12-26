// import { useContext, useEffect, useState } from 'react';
// import { useAuthContext } from '../context/AuthContext';
// import { getToken } from '../utils/authUtils';

// const useAuth = () => {
//   const { authState, login, logout } = useAuthContext();
//   const [isLoading, setIsLoading] = useState<boolean>(true);

//   useEffect(() => {
//     const token = getToken();
//     if (token) {
//       // Simulate fetching user details with a valid token
//       login({
//         accountId: 1, // Replace with actual API call result
//         username: 'test_user',
//         role: 'EMPLOYEE',
//         token,
//       });
//     }
//     setIsLoading(false);
//   }, [login]);

//   return { authState, isLoading, login, logout };
// };

// export default useAuth;
export {}
