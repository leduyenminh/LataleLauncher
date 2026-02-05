import React from 'react';
export default function GamePage() {
  return (
    <div className="page centered narrow">
      <div className="panel">
        <div className="game-header">
          <h2>Welcome to the Realm</h2>
          <p>Launch the client or review your session status.</p>
        </div>
        <div className="game-actions">
          <div className="game-card">
            <h3>Launcher Status</h3>
            <p>All systems are ready. Install or update the client before launching.</p>
            <button className="btn">Launch Client</button>
          </div>
          <div className="game-card">
            <h3>Quick Tips</h3>
            <ul>
              <li>Keep ping low by choosing a nearby server.</li>
              <li>Visit settings to configure graphics and audio.</li>
              <li>Join your party from the social hub.</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}
