"use client";
import Link from "next/link";
import { getAuth } from "@/utils/isAuth";

const NavBar = () => {
  const isAuth = getAuth();

  return (
    <header>
      <nav>
        <ul className="flex justify-between py-6 px-6 bg-[#4D5057]">
          <li className="font-bold text-3xl">
            <Link href="/">StoreIT</Link>
          </li>
          <div className="flex justify-center items-center gap-8 text-lg font-medium px-4 md:px-6 lg:px-20">
            {!isAuth && (
              <>
                <li className="cursor-pointer hover:bg-[#CFCFCF] hover:text-black px-2.5 rounded-md">
                  <Link href="/login">Login</Link>
                </li>
                <li className="cursor-pointer hover:bg-[#CFCFCF] hover:text-black px-2.5 rounded-md">
                  <Link href="/signup">Signup</Link>
                </li>
              </>
            )}
            <li className="cursor-pointer hover:bg-[#CFCFCF] hover:text-black px-2.5 rounded-md">
              <Link href="/about">About</Link>
            </li>
          </div>
        </ul>
      </nav>
    </header>
  );
};
export default NavBar;
