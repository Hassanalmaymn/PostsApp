import "./globals.css";
import NavigationBar from "./navigationBar";
import { AuthProvider } from "../ContextAPIs/AuthContext";
export const metadata = {
  title: "Posts app",
  description: "posts app using Next.js and javascript",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <AuthProvider>
          <NavigationBar>{children}</NavigationBar>
        </AuthProvider>
      </body>
    </html>
  );
}
