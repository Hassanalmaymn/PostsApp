"use client";
import { useState } from "react";
import CreateNewPostModal from "./CreateNewPostModal";

export default function CreateNewPostCard() {
  const [isOpen, setIsOpen] = useState(false);

  const handleOpen = () => {
    setIsOpen(true);
  };

  const handleClose = () => {
    setIsOpen(false);
  };

  return (
    <>
      <div
        className="flex h-fit justify-center rounded-2xl bg-gray-400 shadow-lg p-6 max-w-2xl w-full hover:cursor-pointer"
        onClick={handleOpen}
      >
        <p className="mt-2 justify-center text-center">create a new post</p>
      </div>
      {isOpen && <CreateNewPostModal onClose={handleClose} />}
    </>
  );
}
