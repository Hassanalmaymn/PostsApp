import { api } from "../../api/axios";
export default async function PostsPage({ params }) {
  const { post_id } = params;
  const { user_id } = params;

  const response = await api.get(`/posts/${post_id}`);

  const post = await response.data;

  return (
    <div className="min-h-screen max-h-max p-4 bg-gradient-to-b from-gray-100 to-gray-300 flex flex-col items-center justify-center">
      <div className="pb-4  flex flex-col items-center justify-center">
        <div className="flex flex-col items-center justify-center rounded-2xl bg-gray-400 shadow-lg p-16 max-w-2xl w-full">
          <h2 className="text-2xl font-bold">{post.title}</h2>
          <p className="mt-2">{post.content}</p>
        </div>
      </div>
    </div>
  );
}
