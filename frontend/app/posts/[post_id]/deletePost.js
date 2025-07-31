"use client";
import { Trash2 } from "lucide-react";
import { api } from "@/app/api/axios";
import { useAuth } from "@/ContextAPIs/AuthContext";
import { redirect } from "next/navigation";

export default function DeletePost({ post_id, user_id }) {
  const { user } = useAuth();

  if (!user) {
    return;
  }

  if (!user.isAdmin && user.userId !== user_id) {
    return;
  }

  async function handleDeletePost() {
    const response = await api.post(`/posts/delete/${post_id}`, null, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${user.jwt}`,
      },
    });
    if (response.status === 200) {
      redirect("/posts");
    } else {
      console.error("Failed to delete post:", response.data);
    }
  }

  return (
    <div className="m-6">
      <button onClick={handleDeletePost} className="hover:cursor-pointer">
        <Trash2 className="text-red-600" />
      </button>
    </div>
  );
}
