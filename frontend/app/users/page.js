export default async function UsersPage() {
  const res = await fetch("http://localhost:8083/users", { cache: "no-store" });
  const users = await res.json();
  return (
    <main className="flex flex-col items-center justify-center min-h-screen p-4">
      <h1 className="text-4xl font-bold">List of all Users</h1>
      <ul className="mt-4">
        {users.map((user) => (
          <li key={user.id} className="border-b py-2">
            {user.name}
          </li>
        ))}
      </ul>
    </main>
  );
}
