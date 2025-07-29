"use client";
import { Trash2 } from "lucide-react";
import { api } from "@/app/api/axios";
import { useAuth } from "@/ContextAPIs/AuthContext";

export default function DeletePost({ post_id, user_id }) {
  const { user } = useAuth();
  console.log(user.userId);
  console.log(user_id);
  console.log(user.jwt);

  

  if (!(user && user.isAdmin && user.userId === user_id)) {
    return;
  }

  console.log(post_id);

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
      <button onClick={handleDeletePost} className="hover:cursor-pointer">
        <Trash2 className="text-red-600" />
      </button>
    </div>
  );
}
