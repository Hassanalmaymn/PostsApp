"use client";
import Link from "next/link";
import { useState, useEffect, useRef } from "react";

export default function Search() {
  const [searchQuery, setSearchQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const searchInputRef = useRef(null);
  useEffect(() => {
    async function fetchSearchResults() {
      if (searchQuery.length > 0) {
        const response = await fetch(
          `http://localhost:8083/posts/search?keyword=${searchQuery}`,
          {
            cache: "no-store",
          }
        );
        if (response.ok) {
          const data = await response.json();
          setSearchResults(data);
        } else {
          console.error("Failed to fetch search results");
          setSearchResults([]);
        }
      } else {
        setSearchResults([]);
      }
    }
    fetchSearchResults();
  }, [searchQuery]);

  function handleSearch(e) {
    setSearchQuery(e.target.value);
  }

  return (
    <div className="w-1/3 relative">
      <input
        type="text"
        ref={searchInputRef}
        className="border p-2 rounded w-full"
        placeholder="Search posts..."
        onChange={(e) => {
          handleSearch(e);
        }}
      />
      {searchResults.length > 0 && (
        <div className="absolute left-0 right-0 mt-1 bg-gray-100 shadow-lg rounded-lg p-4 text-gray-700 z-10 h-fit max-h-56 overflow-y-scroll">
          <ul>
            {searchResults.map((post) => (
              <Link
                href={`/posts/${post.id}`}
                key={post.id}
                onClick={() => {
                  setSearchQuery("");
                  setSearchResults([]);
                  searchInputRef.current.value = "";
                }}
              >
                <li
                  key={post.id}
                  className="border-b border-gray-200 hover:bg-gray-300 p-3 rounded-lg"
                >
                  {post.title}
                </li>
              </Link>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}
