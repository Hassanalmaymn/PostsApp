export default function PageStructure({ children, className }) {
  return (
    <div
      className={`min-h-screen max-h-max p-4 bg-gradient-to-b from-gray-200 to-gray-400 flex flex-col items-center justify-center ${className}`}
    >
      {children}
    </div>
  );
}
