"use server";

import { revalidatePath } from "next/cache";
import { redirect } from "next/navigation";
import { api } from "../api/axios";

export async function createPost(prevState, formData) {
  const title = formData.get("title");
  const content = formData.get("content");

  await api.post(
    "/posts",
    { title, content, user: { id: 1 } }, // This is the request body
    {
      headers: {
        "Content-Type": "application/json",
      },
    }
  );

  revalidatePath("/posts");
  redirect("/posts");
}
