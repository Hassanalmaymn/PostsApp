import Link from "next/link";
export default function PostCard({ post }) {
  return (
    <Link href={`/users/${post.user.id}/posts/${post.id}`} className="w-full">
      <div className="flex flex-col items-center justify-center rounded-2xl bg-gray-400 shadow-lg p-6 max-w-2xl w-full">
        <h2 className="text-2xl font-bold">{post.title}</h2>
        <p className="mt-2">{post.content}</p>
      </div>
    </Link>
  );
}
