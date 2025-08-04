"use client";
import { createContext, useContext, useEffect, useState } from "react";
import { api } from "../app/api/axios";

const AuthContext = createContext({
  user: {
    jwt: "",
    userId: "",
    username: "",
    roles: [],
    isAuthinticated: false,
    isAdmin: false,
  },
  login: () => {},
  logout: () => {},
});

export function useAuth() {
  return useContext(AuthContext);
}

export function AuthProvider({ children }) {
  const [user, setUser] = useState({
    jwt: "",
    userId: "",
    username: "",
    roles: [],
    isAuthinticated: false,
    isAdmin: false,
  });

  useEffect(() => {
    const jwt = localStorage.getItem("jwt");
    const storedUser = localStorage.getItem("user");

    if (jwt && storedUser) {
      try {
        const parsedUser = JSON.parse(storedUser);
        console.log("+++_D_D__D__D__");

        console.log(parsedUser);
        console.log("+++_D_D__D__D__");

        setUser({
          jwt,
          userId: parsedUser.userId,
          username: parsedUser.name,
          roles: parsedUser.roles,
          isAuthinticated: true,
          isAdmin: parsedUser.roles.some((role) => role.name === "ROLE_ADMIN"),
        });
      } catch (e) {
        console.error("Failed to parse stored user", e);
        localStorage.removeItem("jwt");
        localStorage.removeItem("user");
      }
    }
  }, []);

  function login(fetchedUser, jwt) {
    localStorage.setItem("jwt", jwt);
    localStorage.setItem("user", JSON.stringify(fetchedUser));
    setUser({
      jwt: jwt,
      userId: fetchedUser.id,
      username: fetchedUser.name,
      roles: fetchedUser.roles,
      isAuthinticated: true,
      isAdmin: fetchedUser.roles.some((role) => role.name === "ROLE_ADMIN"),
    });
    console.log(user);
  }

  async function logout() {
    await api.post("/logout", null, {
      headers: {
        Authorization: `Bearer ${user.jwt}`,
      },
    });
    localStorage.removeItem("jwt");
    localStorage.removeItem("user");
    setUser({
      jwt: "",
      userId: "",
      username: "",
      roles: [],
      isAuthinticated: false,
      isAdmin: false,
    });
  }

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
