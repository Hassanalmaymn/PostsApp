import "./globals.css";
import NavigationBar from "./navigationBar";
export const metadata = {
  title: "Posts app",
  description: "posts app using Next.js and javascript",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <NavigationBar>{children}</NavigationBar>
      </body>
    </html>
  );
}
