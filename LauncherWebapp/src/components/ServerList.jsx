// Server list UI for showing status, population, and ping.
import React from 'react';

/**
 * Renders server cards, including loading and empty states.
 */
function ServerList({ servers, onSelect, loading }) {
  return (
    <div className="server-list">
      <div className="server-list-header">
        <h2>Select Server</h2>
        <p>Pick a realm with the best ping and availability.</p>
      </div>

      {loading ? (
        <div className="server-loading">
          {[...Array(3)].map((_, index) => (
            <div key={index} className="server-card shimmer">
              <div className="shimmer-wave" />
            </div>
          ))}
        </div>
      ) : servers.length === 0 ? (
        <div className="server-empty">No servers available right now.</div>
      ) : (
        servers.map((server) => (
          <div key={server.id} className="server-card">
            <div className="server-left">
              <div className={`status-dot ${server.status}`} />
              <div>
                <div className="server-name">{server.name}</div>
                <div className="server-meta">
                  Status: <span className={`status-pill ${server.status}`}>{server.status}</span>
                </div>
                <div className="server-meta">Population: {server.population}</div>
                <div className="server-meta">Ping: {server.ping} ms</div>
              </div>
            </div>

            <button
              className="btn"
              disabled={server.status !== 'online'}
              onClick={() => onSelect(server.id)}
            >
              Enter
            </button>
          </div>
        ))
      )}
    </div>
  );
}

export default ServerList;
