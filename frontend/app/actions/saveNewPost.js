"use server";

import { revalidatePath } from "next/cache";
import { redirect } from "next/navigation";

export async function createPost(prevState, formData) {
  const title = formData.get("title");
  const content = formData.get("content");

  await fetch("http://localhost:8083/users/1/posts", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ title, content, user: { id: 1 } }),
  });

  revalidatePath("/posts");
  redirect("/posts");
}
