// Page for selecting a server before launching the game.
import React from 'react';
import { useQuery, useMutation } from '@tanstack/react-query';
import { fetchServers, chooseServerApi } from '../api/serverApi';
import ServerList from '../components/ServerList';
import { useNavigate } from 'react-router-dom';

/**
 * Fetches and displays servers, allowing a user to select one.
 */
export default function ServerSelectPage() {
  const navigate = useNavigate();
  const {
    data: servers = [],
    isLoading,
    isError
  } = useQuery(['servers'], fetchServers, {
    staleTime: 30_000
  });

  const choose = useMutation(chooseServerApi, {
    onSuccess: () => navigate('/game')
  });

  return (
    <div className="page centered narrow">
      <div className="panel">
        {isError && <div className="error">Unable to load servers right now.</div>}
        <ServerList
          servers={servers}
          loading={isLoading}
          onSelect={(id) => choose.mutate(id)}
        />
      </div>
    </div>
  );
}
