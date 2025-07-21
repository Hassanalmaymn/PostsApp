export default function PageStructure({ children }) {
  return (
    <div className="min-h-screen max-h-max p-4 bg-gradient-to-b from-gray-100 to-gray-300 flex flex-col items-center justify-center">
      {children}
    </div>
  );
}
