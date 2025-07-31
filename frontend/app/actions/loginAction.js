"use client";

import { api } from "../api/axios";

export async function loginAction(formData) {
  const email = formData.get("email");
  const password = formData.get("password");

  if (!email || !password) {
    return { error: "Email and password are required." };
  }

  try {
    const response = await api.post(
      "/login",
      { email, password },
      { withCredentials: true }
    );

    console.log("Login response:", response.data.user.roles);
    return { user: response.data.user, jwt: response.data.jwt };
  } catch (err) {
    return { error: err?.response?.data?.message || "Login failed." };
  }
}
