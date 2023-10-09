"use client";
import { useState } from "react";

export default function Page() {
  const [showPass, setShowPass] = useState(false);

  return (
    <section className="container relative mx-auto my-96 flex max-w-sm md:max-w-sm lg:max-w-md flex-col px-6 justify-center gap-16 bg-[#999799] py-10 rounded-md text-gray-950">
      <h2 className="font-semibold text-3xl">Login</h2>
      <form className="flex flex-col justify-center gap-4 text-lg font-semibold">
        <div className="flex flex-col">
          <label htmlFor="username" className="mb-2 text-xl">
            Username
          </label>
          <input
            type="text"
            id="username"
            className="rounded-sm outline-none py-0.5 px-1 shadow-md focus:shadow-xl hover:shadow-xl"
          />
        </div>
        <div className="flex flex-col">
          <label htmlFor="password" className="mb-2 text-xl">
            Password
          </label>
          <input
            type="password"
            id="password"
            className="rounded-sm outline-none py-0.5 px-1 shadow-md focus:shadow-xl hover:shadow-xl"
          />
        </div>
        <button
          className="rounded-md bg-slate-600 mt-4 py-2 w-36 mx-auto shadow-md hover:shadow-xl hover:bg-white"
          type="submit"
        >
          Submit
        </button>
      </form>
    </section>
  );
}
