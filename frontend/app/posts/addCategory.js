import { useState } from "react";
import { api } from "../api/axios";
import { useAuth } from "@/ContextAPIs/AuthContext";

export default function AddCategoryModal({ isOpen, onClose }) {
  const [categoryName, setCategoryName] = useState("");
  const { user } = useAuth();

  const handleAddCategory = async () => {
    if (!categoryName.trim()) {
      alert("Please enter a category name.");
      return;
    }

    try {
      const response = await api.post(
        "/categories",
        {
          category_name: categoryName,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${user.jwt}`,
          },
        }
      );
      console.log("Category added:", response.data);
      setCategoryName("");
      onClose();
    } catch (error) {
      console.error("Error adding category:", error);
      alert("Failed to add category. Please try again.");
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-md relative">
        <button
          onClick={onClose}
          className="absolute top-2 right-2 text-gray-600 hover:text-black"
        >
          ✕
        </button>
        <h2 className="text-xl font-semibold mb-4 text-center">
          Add New Category
        </h2>
        <input
          type="text"
          value={categoryName}
          onChange={(e) => setCategoryName(e.target.value)}
          placeholder="Enter new category name"
          className="w-full px-4 py-2 mb-4 border border-gray-300 rounded-lg"
        />
        <button
          onClick={handleAddCategory}
          className="w-full bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg"
        >
          Add Category
        </button>
      </div>
    </div>
  );
}
