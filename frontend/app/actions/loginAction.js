import { api } from "../api/axios";

export async function loginAction(formData) {
  const email = formData.get("email");
  const password = formData.get("password");

  const params = new URLSearchParams();
  params.append("username", email);
  params.append("password", password);

  try {
    const loginResponse = await api.post("/login", params, {
      withCredentials: true,
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    });

    console.log("Login successful", loginResponse);

    const userRes = await api.post(
      "/getUserByEmail",
      { email },
      {
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    return { user: userRes.data };
  } catch (err) {
    console.error("Login failed:", err?.response?.data || err.message);
    return { error: "Invalid credentials or server error." };
  }
}
