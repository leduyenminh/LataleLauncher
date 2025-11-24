import api from './axiosInstance';

export const fetchServers = async () => {
  const res = await api.get("/api/servers");
  return res.data; // [{id, name, status, population, ping}]
};

export const chooseServerApi = async (serverId) => {
  const res = await api.post(`/api/servers/${serverId}/select`);
  return res.data; // success response
};
