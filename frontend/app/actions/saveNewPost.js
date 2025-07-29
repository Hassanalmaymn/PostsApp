"use server";

import { revalidatePath } from "next/cache";
import { redirect } from "next/navigation";
import { api } from "../api/axios";
import { useAuth } from "@/ContextAPIs/AuthContext";

export async function createPost(prevState, formData) {
  const catIds = formData.getAll("category");
  const title = formData.get("title");
  const content = formData.get("content");
  const jwt = formData.get("jwt");
  const post = {
    title,
    content,
    categories: catIds.map((id) => ({ id: Number(id) })),
  };

  await api.post("/posts", post, {
    headers: {
      "Content-Type": "application/json",
      Authorization: `Beare ${jwt}`,
    },
  });

  revalidatePath("/posts");
  redirect("/posts");
}
