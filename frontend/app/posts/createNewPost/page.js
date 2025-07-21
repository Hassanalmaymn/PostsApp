import PageStructure from "@/app/pageStructure";
export default function createNewPostPage() {
  return (
    <PageStructure>
      <h1 className="text-4xl font-bold">Create a New Post</h1>
      <p className="mt-2">Fill in the details below to create a new post.</p>
    </PageStructure>
  );
}
