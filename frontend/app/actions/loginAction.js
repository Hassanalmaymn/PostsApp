import { api } from "../api/axios";

export async function loginAction(formData) {
  const email = formData.get("email");
  const password = formData.get("password");

  try {
    const loginResponse = await api.post(
      "/login",
      { email, password },
      {
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    return loginResponse.data;
  } catch (err) {
    console.error("Login failed:", err?.response?.data || err.message);
    return { error: "Invalid credentials or server error." };
  }
}
