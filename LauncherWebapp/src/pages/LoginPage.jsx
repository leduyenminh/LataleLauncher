import React from 'react';
import { useMutation } from '@tanstack/react-query';
import { loginApi } from '../api/authApi';
import LoginForm from '../components/LoginForm';
import { useAuth } from '../context/AuthProvider';
import { useNavigate } from 'react-router-dom';
import AnimatedLogo from '../components/AnimatedLogo';

export default function LoginPage() {
  const { setAuth } = useAuth();
  const navigate = useNavigate();

  const loginMutation = useMutation(loginApi, {
    onSuccess: (data) => {
      // data: { token, user }
      const { token, user } = data;
      setAuth({ token, user });
      navigate('/servers');
    }
  });

  return (
    <div className="page centered">
      <AnimatedLogo />
      <LoginForm onLogin={(payload) => loginMutation.mutateAsync(payload)} />
    </div>
  );
}
