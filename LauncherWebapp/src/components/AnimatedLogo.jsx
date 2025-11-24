import React from 'react';
import { motion } from 'framer-motion';
import './logo.css'; // optional for extra styling

export default function AnimatedLogo() {
  return (
    <motion.div
      initial={{ opacity: 0, y: -12 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.8, ease: 'easeOut' }}
      className="logo-wrap"
    >
      <div className="logo-glow" />
      <h1 className="game-title">Aether Realms</h1>
      <p className="game-sub">MMORPG Launcher</p>
    </motion.div>
  );
}
