import axios from 'axios';
import { logout } from './authApi';

const api = axios.create({
  baseURL: '/api', // change if needed
  timeout: 15000,
});

// Attach token to requests
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('access_token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Global response error handler
api.interceptors.response.use(
  (res) => res,
  (err) => {
    if (err.response && err.response.status === 401) {
      // token expired or invalid -> force logout
      localStorage.removeItem('access_token');
      // Optionally redirect to login (can't import history here)
      // call a logout endpoint if your backend requires it
      // logout(); // uncomment if you export logout to clear server session
    }
    return Promise.reject(err);
  }
);

export default api;
