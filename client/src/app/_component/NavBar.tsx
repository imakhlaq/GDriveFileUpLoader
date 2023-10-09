"use client";
import Link from "next/link";
import { getAuth } from "@/utils/isAuthenticated";

const NavBar = () => {
  const isAuth = getAuth();

  return (
    <header>
      <nav>
        <ul className="flex justify-between py-6 px-6">
          <li className="font-bold text-3xl">
            <Link href="/">StoreIT</Link>
          </li>
          <div className="flex gap-8 text-lg">
            {!isAuth && (
              <>
                <li className="cursor-pointer">
                  <Link href="/login">Login</Link>
                </li>
                <li className="cursor-pointer">
                  <Link href="/signup">Signup</Link>
                </li>
              </>
            )}
            <li className="cursor-pointer">
              <Link href="/about">About</Link>
            </li>
          </div>
        </ul>
      </nav>
    </header>
  );
};
export default NavBar;
