import Link from "next/link";
import Search from "./Search";
export default function NavigationBar({ children }) {
  return (
    <>
      <nav className="flex items-center p-4 bg-gray-800 text-white">
        <div className="mr-12">
          <h1 className="text-2xl font-bold">Posts App</h1>
        </div>
        <ul className="flex space-x-4 mr-52">
          <li>
            <Link href="/">Home</Link>
          </li>
          <li>
            <Link href="/posts">posts</Link>
          </li>
        </ul>
        <Search />
      </nav>
      {children}
      <footer className="m-4 text-center bg-gradient-to-b from-gray-200 to-gray-400">
        <p>&copy; {new Date().getFullYear()} Posts App</p>
      </footer>
    </>
  );
}
