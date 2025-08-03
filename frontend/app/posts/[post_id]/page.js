import { api } from "../../api/axios";
import DeletePost from "./deletePost";
import { useAuth } from "@/ContextAPIs/AuthContext";

export default async function PostsPage({ params }) {
  const { post_id } = await params;

  const response = await api.get(`/posts/${post_id}`);

  const post = response.data;

  return (
    <div className="min-h-screen max-h-max p-4 bg-gradient-to-b from-gray-100 to-gray-300 ">
      <div className="pb-4 w-full flex flex-col items-center justify-center">
        <div className="flex flex-col items-center border-gray-800 justify-center rounded-2xl bg-gray-200 shadow-lg p-16 max-w-2xl w-full">
          <div>
            <h2 className="text-2xl font-bold">{post.title}</h2>
            <p className="mt-2">{post.content}</p>
          </div>
          <img src={post.imageUrl} alt={post.title} className="mt-4 rounded-lg" />
          {console.log(post)}
          <DeletePost post_id={post_id} user_id={post.user_id} />
        </div>
      </div>
    </div>
  );
}
