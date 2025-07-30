"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import Search from "./Search";
import { useAuth } from "@/ContextAPIs/AuthContext";

export default function NavigationBar({ children }) {
  const pathname = usePathname();
  const { user, logout } = useAuth();

  const isAuthRoute = pathname === "/login" || pathname === "/signup";

  return (
    <>
      <nav className="bg-gray-900 text-white shadow-md">
        <div className="max-w-7xl mx-auto px-4 py-3 flex justify-between items-center">

          <div className="text-2xl font-extrabold tracking-wide text-blue-400">
            <Link href="/">Posts App</Link>
          </div>


          <ul className="flex space-x-6 text-sm font-medium">
            <li>
              <Link
                href="/"
                className={`hover:text-blue-400 ${
                  pathname === "/" ? "text-blue-400" : ""
                }`}
              >
                Home
              </Link>
            </li>
            <li>
              <Link
                href="/posts"
                className={`hover:text-blue-400 ${
                  pathname.startsWith("/posts") ? "text-blue-400" : ""
                }`}
              >
                Posts
              </Link>
            </li>
          </ul>
          <Search />

          <div className="flex items-center space-x-4">
            
            {!user.isAuthinticated && !isAuthRoute && (
              <>
                <Link
                  href="/login"
                  className="px-3 py-1 bg-blue-500 hover:bg-blue-600 rounded-md text-white text-sm"
                >
                  Login
                </Link>
                <Link
                  href="/signup"
                  className="px-3 py-1 bg-green-500 hover:bg-green-600 rounded-md text-white text-sm"
                >
                  Sign Up
                </Link>
              </>
            )}

            {user.isAuthinticated && (
              <button
                onClick={logout}
                className="px-3 py-1 bg-red-500 hover:bg-red-600 rounded-md text-white text-sm"
              >
                Logout
              </button>
            )}
          </div>
        </div>
      </nav>

      {children}

      <footer className="mt-10 py-4 text-center bg-gradient-to-r from-gray-200 to-gray-400 text-gray-800">
        <p>&copy; {new Date().getFullYear()} Posts App</p>
      </footer>
    </>
  );
}
