import Link from "next/link";
export default function PostCard({ post }) {
  return (
    <Link href={`/posts/${post.id}`} className="w-full">
      <div className="flex flex-col items-center justify-center rounded-2xl bg-gray-400 shadow-lg p-6 max-w-2xl w-full">
        <div>
          <p className="mt-2 justify-center text-center">
            <span className="font-bold">{post.title}</span>
            <br />
            {post.content}
          </p>
        </div>
       {post.imageUrl?.length > 0 && <div>
          <img
            src={post.imageUrl}
            alt={post.title}
            className="mt-4 rounded-lg"
          />
        </div>}
      </div>
    </Link>
  );
}
