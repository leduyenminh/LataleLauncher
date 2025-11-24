import React from "react";

function ServerList({ servers, onSelect }) {
  return (
    <div className="server-list">
      <h2>Select Server</h2>

      {servers.map((server) => (
        <div key={server.id} className="server-row">
            <div className="server-info">
                <strong>{server.name}</strong>
                <p>Status: <span className={server.status}>{server.status}</span></p>
                <p>Population: {server.population}</p>
                <p>Ping: {server.ping} ms</p>
            </div>

            <button
                disabled={server.status !== "online"}
                onClick={() => onSelect(server.id)}
            >
                Enter
            </button>
        </div>
      ))}
    </div>
  );
}

export default ServerList;
