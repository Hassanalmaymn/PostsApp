"use client";
import { createContext, useContext, useState } from "react";

const AuthContext = createContext({
  user: {
    jwt: "",
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
  function login(user, jwt) {
    console.log(user.name + "__" + user.role + "==" + jwt);
    setUser({
      jwt,
      username: user.name,
      role: user.role,
      isAuthinticated: true,
      isAdmin: user.role == "ROLE_ADMIN",
    });
  }
  function logout() {}
  return <AuthContext value={{ user, login, logout }}>{children}</AuthContext>;
}
