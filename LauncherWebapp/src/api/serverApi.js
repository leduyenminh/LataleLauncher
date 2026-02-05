import api from './axiosInstance';

export const fetchServers = async () => {
  const res = await api.get('servers');
  return res.data; // [{id, name, status, population, ping}]
};

export const chooseServerApi = async (serverId) => {
  const res = await api.post(`servers/${serverId}/select`);
  return res.data; // success response
};
