import React from 'react';
import { motion } from 'framer-motion';

export default function ServerCard({ server, onEnter }) {
  const online = server.status === 'online';
  return (
    <motion.div
      className="server-card"
      whileHover={{ scale: 1.02 }}
      transition={{ type: 'spring', stiffness: 300 }}
    >
      <div className="server-left">
        <div className={`status-dot ${online ? 'online' : 'offline'}`} />
        <div>
          <div className="server-name">{server.name}</div>
          <div className="server-meta">
            {online ? server.population || 'Population: Unknown' : 'Offline'}
            {' • '}
            Ping: {server.ping ?? '-'} ms
          </div>
        </div>
      </div>
      <div className="server-actions">
        <button className="btn" disabled={!online} onClick={() => onEnter(server.id)}>
          {online ? 'Enter' : 'Unavailable'}
        </button>
      </div>
    </motion.div>
  );
}
