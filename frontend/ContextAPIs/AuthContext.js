"use client";
import { createContext, useContext, useEffect, useState } from "react";

const AuthContext = createContext({
  user: {
    jwt: "",
    userId: "",
    username: "",
    role: "",
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
    role: "",
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
          role: parsedUser.role,
          isAuthinticated: true,
          isAdmin: parsedUser.role === "ROLE_ADMIN",
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
    localStorage.setItem("user", JSON.stringify(user));
    setUser({
      jwt: jwt,
      userId: fetchedUser.id,
      username: fetchedUser.name,
      role: fetchedUser.role,
      isAuthinticated: true,
      isAdmin: user.role === "ROLE_ADMIN",
    });
    console.log(user);
  }

  function logout() {
    localStorage.removeItem("jwt");
    localStorage.removeItem("user");
    setUser({
      jwt: "",
      userId: "",
      username: "",
      role: "",
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
