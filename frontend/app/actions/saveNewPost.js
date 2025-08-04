"use server";

import { revalidatePath } from "next/cache";
import { redirect } from "next/navigation";
import { api } from "../api/axios";

export async function createPost(prevState, formData) {
  const imageFormData = new FormData();
  const catIds = formData.getAll("category");
  const title = formData.get("title");
  const content = formData.get("content");
  const jwt = formData.get("jwt");
  const user_id = formData.get("user_id");
  const image = formData.get("image");
  imageFormData.append("file", image);

  const imageUrl = await api.post("/posts/image", imageFormData, {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: `Bearer ${jwt}`,
    },
  });
  const post = {
    title,
    content,
    imageUrl: imageUrl.data.url,
    user: {
      id: user_id,
    },
    categories: catIds.map((id) => ({ id: Number(id) })),
  };
  if (imageUrl.status === 200) {
    await api.post("/posts/create", post, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwt}`,
      },
    });
  }

  revalidatePath("/posts");
  redirect("/posts");
}
