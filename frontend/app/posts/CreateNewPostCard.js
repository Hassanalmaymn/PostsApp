import Link from "next/link";
export default function CreateNewPostCars() {
  return (
    <Link href={`/posts/createNewPost`} className="w-full">
      <div className="flex flex-col items-center justify-center rounded-2xl bg-gray-400 shadow-lg border-dashed p-6 max-w-2xl w-full">
        <p className="mt-2">create new post</p>
      </div>
    </Link>
  );
}
