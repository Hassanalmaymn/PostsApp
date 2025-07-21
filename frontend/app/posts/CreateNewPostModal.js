"use client";

import { useActionState } from "react";
import { createPost } from "../actions/saveNewPost";

export default function CreateNewPostModal({ onClose }) {
  const [state, formAction] = useActionState(createPost, null);

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50">
      <div className="bg-white rounded-lg shadow-lg p-6 max-w-md w-full">
        <h2 className="text-2xl font-bold mb-4">Create New Post</h2>
        <form action={formAction}>
          <div className="mb-4">
            <label className="block text-sm font-medium mb-2">Title</label>
            <input
              type="text"
              className="w-full p-2 border border-gray-300 rounded"
              placeholder="Enter post title"
              name="title"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium mb-2">Content</label>
            <textarea
              className="w-full p-2 border border-gray-300 rounded"
              placeholder="Enter post content"
              name="content"
              required
            ></textarea>
          </div>
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600"
          >
            Create Post
          </button>
        </form>
        <button
          onClick={onClose}
          className="mt-4 w-full bg-gray-300 py-2 rounded hover:bg-gray-400"
        >
          Close
        </button>
      </div>
    </div>
  );
}
