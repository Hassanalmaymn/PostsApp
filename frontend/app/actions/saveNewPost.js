"use server";

import { revalidatePath } from "next/cache";
import { redirect } from "next/navigation";
import { api } from "../api/axios";

export async function createPost(prevState, formData) {
  const catIds = formData.getAll("category");
  const title = formData.get("title");
  const content = formData.get("content");
  const jwt = formData.get("jwt");
  const user_id = formData.get("user_id");
  const post = {
    title,
    content,
    user: {
      id: user_id,
    },
    categories: catIds.map((id) => ({ id: Number(id) })),
  };

  await api.post("/posts", post, {
    headers: {
      "Content-Type": "application/json",
    },
  });

  revalidatePath("/posts");
  redirect("/posts");
}
