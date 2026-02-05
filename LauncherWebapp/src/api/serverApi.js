// API helpers for server list and selection flows.
import api from './axiosInstance';

/**
 * Fetches the list of servers available for selection.
 */
export const fetchServers = async () => {
  const res = await api.get('servers');
  return res.data; // [{id, name, status, population, ping}]
};

/**
 * Sends the selected server choice to the backend.
 */
export const chooseServerApi = async (serverId) => {
  const res = await api.post(`servers/${serverId}/select`);
  return res.data; // success response
};
