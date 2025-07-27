"use client";
import { createContext, useContext, useState } from "react";

const AuthContext = createContext({
  user: {
    username: "",
    role: "user",
    isAuthinticated: false,
  },
  login: () => {},
  logout: () => {},
});
export function useAuth() {
  return useContext(AuthContext);
}

export function AuthProvider({ children }) {
  const [user, setUser] = useState({
    username: "",
    role: "user",
    isAuthinticated: false,
  });
  function login() {}
  function logout() {}
  return <AuthContext value={{ user, login, logout }}>{children}</AuthContext>;
}
