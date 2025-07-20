export default async function PostsPage({ params }) {
  const { post_id } = params;

  const response = await fetch(
    `http://localhost:8083/users/1/posts/${post_id}`
  );

  const post = await response.json();

  console.log(post);

  return (
    <div className="min-h-screen p-4">
      <div className="pb-4  flex flex-col items-center justify-center">
        <h2 className="text-2xl font-bold">{post.title}</h2>
        <p className="mt-2">{post.content}</p>
      </div>
    </div>
  );
}
