import PostCard from "./postCard";
import CreateNewPostCard from "./CreateNewPostCard";
import { api } from "../api/axios";
export default async function PostsPage() {
  const response = await api.get("/posts");
  const posts = response.data;

  return (
    <main className="flex flex-col min-h-screen p-4 bg-gradient-to-b from-gray-100 to-gray-300">
      <h1 className="text-4xl font-bold mb-4">List of all Posts</h1>

      <div className="m-4 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        <CreateNewPostCard />
        {posts.map((post) => (
          <PostCard key={post.id} post={post} />
        ))}
      </div>
    </main>
  );
}
