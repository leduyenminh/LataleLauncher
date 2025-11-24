import React from 'react';
export default function Shimmer({ height = 16, width = '100%', style = {} }) {
  return (
    <div className="shimmer" style={{ height, width, ...style }}>
      <div className="shimmer-wave" />
    </div>
  );
}
