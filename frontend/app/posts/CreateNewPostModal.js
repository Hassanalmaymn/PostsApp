"use client";

import { useActionState, useEffect, useState, useRef } from "react";
import { createPost } from "../actions/saveNewPost";
import { api } from "../api/axios";
import { useAuth } from "@/ContextAPIs/AuthContext";

export default function CreateNewPostModal({ onClose }) {
  const { user } = useAuth();
  const ref = useRef();

  const [state, formAction] = useActionState(createPost, null);
  const [selectedCategory, setSelectedCategory] = useState([]);
  const [categories, setCategories] = useState([]);
  function handleUploadImage() {
    ref.current.click();
  }

  useEffect(() => {
    async function fetchCategories() {
      const response = await api.get("/categories");
      console.log(response);
      if (response.status === 200) {
        const data = response.data;
        setCategories(data);
      } else {
        console.error("Failed to fetch categories");
      }
    }
    fetchCategories();
  }, []);

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50">
      <div className="bg-white rounded-lg shadow-lg p-6 max-w-md w-full">
        <h2 className="text-2xl font-bold mb-4">Create New Post</h2>
        <form action={formAction}>
          <div className="mb-4">
            <label className="block text-sm font-medium mb-2">Title</label>
            <input type="hidden" value={user.jwt} name="jwt" />
            <input type="hidden" value={user.userId} name="user_id" />
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
          <div className="mb-4">
            <input type="file" accept="image/*" name="image" ref={ref} className="hidden" />
            <button onClick={handleUploadImage} type="button">
              Upload image
            </button>
          </div>
          <div className="mb-4">
            <label className="block text-sm font-semibold mb-2 text-gray-700">
              Select Categories
            </label>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-3">
              {categories.map((category) => (
                <label
                  key={category.id}
                  htmlFor={`category-${category.id}`}
                  className="flex items-center space-x-2 p-2 border rounded-lg cursor-pointer transition-all hover:shadow-md bg-white"
                >
                  <input
                    type="checkbox"
                    value={category.id}
                    id={`category-${category.id}`}
                    className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                    name="category"
                    onChange={(e) => {
                      const id = parseInt(e.target.value);
                      if (e.target.checked) {
                        setSelectedCategory((prev) => [...prev, id]);
                      } else {
                        setSelectedCategory((prev) =>
                          prev.filter((cid) => cid !== id)
                        );
                      }
                    }}
                  />

                  <span className="text-sm text-gray-800">
                    {category.category_name}
                  </span>
                </label>
              ))}
            </div>
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
