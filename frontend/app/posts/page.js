"use client";

import PostCard from "./postCard";
import CreateNewPostCard from "./CreateNewPostCard";
import { useState, useEffect } from "react";
import Pagination from "./pagination";
import { api } from "../api/axios";
import { Loader2 } from "lucide-react";
import { useAuth } from "@/ContextAPIs/AuthContext";
import AddCategoryModal from "./addCategory";
const pageSize = 5;

export default function PostsPage() {
  const [posts, setPosts] = useState([]);
  const [filters, setFilters] = useState([]);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(true);
  const [addCategory, setAddCategory] = useState(false);
  const { user } = useAuth();
  function handlePageNumberChangeNext() {
    console.log(page + " " + totalPages);
    if (page >= totalPages - 1) {
      return;
    }

    setPage((prevPageNumber) => ++prevPageNumber);
  }
  function handlePageNumberChangePrevious() {
    if (page <= 0) {
      return;
    }

    setPage((prevPageNumber) => --prevPageNumber);
  }
  function handleAddCategory() {
    setAddCategory((prev) => !prev);
  }
  async function handleDownloadPosts() {
    try {
      const response = await api.get("/reports/posts.xlsx", {
        responseType: "blob",
        headers: {
          Authorization: `Bearer ${user.jwt}`,
        },
      });
      console.log(`Response status: ${response.status}`);
      
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "posts.xlsx");
      document.body.appendChild(link);
      link.click();
      link.remove();
      window.URL.revokeObjectURL(url);
    } catch (error) {
      console.log("Error downloading posts:");
    }
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [postsRes, filtersRes] = await Promise.all([
          api.get(`/posts?pageNumber=${page}&pageSize=${pageSize}`),
          api.get("/categories"),
        ]);
        setPosts(postsRes.data.content);
        setFilteredPosts(postsRes.data.content);
        setPage(postsRes.data.pageable.pageNumber);
        setTotalPages(postsRes.data.totalPages);
        setFilters(filtersRes.data);
      } catch (err) {
        console.error("Failed to fetch data", err);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [page]);

  const handleFilterChange = (e) => {
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
  };

  return (
    <main className="min-h-screen bg-gradient-to-b from-slate-50 to-slate-200 p-6">
      <div className="max-w-7xl mx-auto">
        <h1 className="text-3xl sm:text-4xl font-extrabold text-gray-800 mb-6 text-center">
          Explore Posts
        </h1>

        <div className="flex flex-row justify-center mb-6">
          <select
            className="w-full sm:w-1/2 md:w-1/3 px-4 py-2 rounded-xl border border-gray-400 bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
            onChange={handleFilterChange}
          >
            <option value="">All Categories</option>
            {filters.map((filter) => (
              <option key={filter.id} value={filter.category_name}>
                {filter.category_name}
              </option>
            ))}
          </select>
          <div className="ml-4">
            <button
              className="px-4 py-2 rounded-xl border border-gray-700 bg-gray-400 shadow-sm hover:bg-gray-500"
              onClick={handleAddCategory}
            >
              Add new category
            </button>
            <AddCategoryModal
              isOpen={addCategory}
              onClose={handleAddCategory}
            />
          </div>
          <div className="ml-4">
            <button
              className="px-4 py-2 rounded-xl border border-gray-700 bg-gray-400 shadow-sm hover:bg-gray-500"
              onClick={handleDownloadPosts}
            >
              Download Posts
            </button>
          </div>
        </div>

        {loading ? (
          <div className="flex justify-center items-center py-20">
            <Loader2 className="animate-spin w-8 h-8 text-gray-500" />
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-3 lg:grid-cols-4 gap-6">
            {user.isAuthinticated && <CreateNewPostCard />}

            {filteredPosts.length === 0 ? (
              <div className="col-span-full text-center text-gray-500 text-lg mt-8">
                No posts found for the selected category.
              </div>
            ) : (
              filteredPosts.map((post) => (
                <PostCard key={post.id} post={post} />
              ))
            )}
          </div>
        )}
      </div>
      <Pagination
        page={page}
        totalPages={totalPages}
        onNext={handlePageNumberChangeNext}
        onPrevious={handlePageNumberChangePrevious}
      />
    </main>
  );
}
