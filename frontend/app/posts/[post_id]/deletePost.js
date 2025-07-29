"use client";
import { Trash2 } from "lucide-react";
import { api } from "@/app/api/axios";
import { useAuth } from "@/ContextAPIs/AuthContext";

export default function DeletePost({ post_id }) {
  const { user } = useAuth();
  console.log(user.jwt);
  async function handleDeletePost() {
    api.post(`/posts/delete/${post_id}`, null, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${user.jwt}`,
      },
    });
  }

  return (
    <div className="m-6">
      <button onClick={handleDeletePost}>
        <Trash2 className="text-red-600" />
      </button>
    </div>
  );
}
