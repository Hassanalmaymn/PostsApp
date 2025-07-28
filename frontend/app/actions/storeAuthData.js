import { useAuth } from "@/ContextAPIs/AuthContext";

export default function AuthHandler({ user }) {
  const { login } = useAuth();
  login(user);
  return <></>;
}
