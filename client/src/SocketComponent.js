import React, { useEffect, useState } from 'react';
import socketIOClient from 'socket.io-client';

const SocketComponent = () => {
  const [message, setMessage] = useState('');

  useEffect(() => {
    const socket = socketIOClient(`chess_validations:4000`); // Replace with your server URL

    // Listen for 'chat message' event
    socket.on('chat message', (message) => {
      setMessage(message);
    });

    // Clean up the socket connection
    return () => {
      socket.disconnect();
    };
  }, []);

  return (
    <div>
      <h2>Socket.IO Example</h2>
      <p>Received message: {message}</p>
    </div>
  );
};

export default SocketComponent;