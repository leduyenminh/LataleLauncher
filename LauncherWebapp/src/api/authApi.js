import api from './axiosInstance';

export const loginApi = async ({ username, password }) => {
  const res = await api.post('api/public/login', { username, password });
  // expected: { token: 'jwt-token', user: {...} }
  return res.data;
};

export const logout = async () => {
  try {
    await api.post('api/public/logout');
  } catch (e) {
    // ignore
  } finally {
    localStorage.removeItem('access_token');
  }
};
