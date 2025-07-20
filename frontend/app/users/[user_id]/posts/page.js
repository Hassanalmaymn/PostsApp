export default async function PostsPage() {
  const response = await fetch("http://localhost:8083/users/1/posts", {
    cache: "no-store",
  });
  const posts = await response.json();

  return (
    <main className="flex flex-col items-center justify-center min-h-screen p-4">
      <h1 className="text-4xl font-bold">Posts</h1>

      <ul className="mt-4">
        {posts.map((post) => (
          <li key={post.id} className="border-b py-2">
            <h2 className="text-2xl font-bold">{post.title}</h2>
            <p className="mt-1">{post.content}</p>
          </li>
        ))}
      </ul>
    </main>
  );
}
