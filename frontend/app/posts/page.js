"use client";
import PostCard from "./postCard";
import CreateNewPostCard from "./CreateNewPostCard";
import { useState, useEffect } from "react";
import { api } from "../api/axios";
export default function PostsPage() {
  const [posts, setPosts] = useState([]);
  const [filters, setFilters] = useState([]);
  const [filteredPosts, setFilteredPosts] = useState([]);

  useEffect(() => {
    const fetchPosts = async () => {
      const response = await api.get("/posts");
      console.log(response.data);
      setPosts(response.data);
      setFilteredPosts(response.data);
    };
    const fetchFilters = async () => {
      const response = await api.get("/categories");
      console.log(response.data);
      setFilters(response.data);
    };
    fetchPosts();
    fetchFilters();
  }, []);

  return (
    <main className="flex flex-col min-h-screen p-4 bg-gradient-to-b from-gray-100 to-gray-300">
      <h1 className="text-4xl font-bold mb-4">List of all Posts</h1>
      <select
        className="mb-4 p-2 w-1/3 border-2 border-gray-600 rounded"
        onChange={(e) => {
          const selectedCategory = e.target.value;

          if (selectedCategory) {
            setFilteredPosts(
              posts.filter((post) =>
                post.categories?.some(
                  (category) =>
                    category.category_name.toLowerCase() ===
                    selectedCategory.toLowerCase()
                )
              )
            );
          } else {
            setFilteredPosts(posts);
          }
        }}
      >
        {filters.length > 0 ? (
          <>
            <option value="">All Categories</option>
            {filters.map((filter) => (
              <option key={filter.id} value={filter.id}>
                {filter.category_name}
              </option>
            ))}
          </>
        ) : (
          <option value="">All Categories</option>
        )}
      </select>

      <div className="m-4 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        <CreateNewPostCard />
        {filteredPosts.length === 0 && (
          <div className="col-span-3 text-center text-gray-500">
            No posts found.
          </div>
        )}
        {filteredPosts.map((post) => (
          <PostCard key={post.id} post={post} />
        ))}
      </div>
    </main>
  );
}
