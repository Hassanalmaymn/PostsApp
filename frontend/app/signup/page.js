"use client";
import PageStructure from "../pageStructure";
import { useActionState } from "react"
import  SignupAction  from "../actions/signupAction";

export default function Signup() {
  const [state, formAction] = useActionState(SignupAction, null);

  return (
    <PageStructure>
      <h1 className="text-3xl font-bold text-center mt-10">Sign Up</h1>

      <div className="w-1/2 mt-10 p-6 bg-gray-300 rounded-lg shadow-lg border border-gray-400 ">
        <form action={formAction}>
          <div className="m-2">
            <label
              htmlFor="username"
              className="block text-xl font-medium text-gray-700"
            >
              Username
            </label>
            <input
              id="username"
              name="username"
              type="text"
              required
              className="mt-1 block w-full p-4 rounded-md border-gray-700 ring-1 ring-gray-700 shadow-sm focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div className="m-2">
            <label
              htmlFor="email"
              className="block text-xl font-medium text-gray-700"
            >
              Email
            </label>
            <input
              id="email"
              name="email"
              type="email"
              required
              className="mt-1 p-4 ring-1 ring-gray-700 block w-full rounded-md border-gray-700 focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div className="m-2">
            <label
              htmlFor="password"
              className="block text-xl font-medium text-gray-700"
            >
              Password
            </label>
            <input
              id="password"
              name="password"
              type="password"
              required
              className="mt-1 ring-1 ring-gray-700 p-4 block w-full rounded-md border-gray-700 shadow-sm focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div className="m-2">
            <label
              htmlFor="confirmPassword"
              className="block text-xl border-gray-700 font-medium m-2 text-gray-700 "
            >
              Confirm Password
            </label>
            <input
              id="confirmPassword"
              name="confirmPassword"
              type="password"
              required
              className="mt-1 p-4 block w-full rounded-md ring-1 ring-gray-700 shadow-sm border-gray-700 focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <button
            type="submit"
            className="w-full py-2 px-4 bg-blue-600 text-white font-semibold rounded-md hover:bg-blue-700 transition"
          >
            Create Account
          </button>

          {state && (
            <p className="text-red-600 text-sm mt-2">{state.message}</p>
          )}
        </form>
      </div>
    </PageStructure>
  );
}
