import { api } from "../api/axios";
export default async function SignupAction(prevState, formData) {
  const username = formData.get("username");
  const email = formData.get("email");
  const role = "ROLE_USER";
  const password = formData.get("password");
  const confirmPassword = formData.get("confirmPassword");
  if (!username || !email || !password || !confirmPassword) {
    return { error: "All fields are required." };
  }
  if (password !== confirmPassword) {
    return { error: "Passwords do not match." };
  }
  const user = {
    name: username,
    email,
    role,
    password,
  };
  console.log("user", user);
  
  try {
    const response = await api.post("/register", user, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return { success: true, message: response.data.message };
  } catch (err) {
    return { error: err?.response?.data?.message || "Signup failed." };
  }
}
