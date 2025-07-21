import Link from "next/link";
export default function NavigationBar({ children }) {
  return (
    <>
      <nav>
        <ul className="flex space-x-4">
          <li>
            <Link href="/">Home</Link>
          </li>
          <li>
            <Link href="/posts">posts</Link>
          </li>
        </ul>
      </nav>
      {children}
      <footer className="m-4 text-center">
        <p>&copy; {new Date().getFullYear()} Posts App</p>
      </footer>
    </>
  );
}
