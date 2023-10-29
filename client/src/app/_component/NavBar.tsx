"use client";
import Link from "next/link";
import { getAuth } from "@/utils/isAuth";
import { useEffect, useRef, useState } from "react";

const NavBar = () => {
  const [isAuth, setIsAuth] = useState<AuthRes | null>(null);

  useEffect(() => {
    setIsAuth(getAuth());
    console.log(isAuth);
  }, []);

  return (
    <header>
      <nav>
        <ul className="flex justify-between py-6 px-6 bg-[#4D5057]">
          <li className="font-bold text-3xl">
            <Link href="/">StoreIT</Link>
          </li>
          <div className="flex justify-center items-center gap-8 text-lg font-medium px-4 md:px-6 lg:px-20">
            {!isAuth?.username && (
              <>
                <li className="cursor-pointer hover:bg-[#CFCFCF] hover:text-black px-2.5 rounded-3xl">
                  <Link href="/login">Login</Link>
                </li>
                <li className="cursor-pointer hover:bg-[#CFCFCF] hover:text-black px-2.5 rounded-3xl">
                  <Link href="/signup">Signup</Link>
                </li>
              </>
            )}
            {isAuth?.username && (
              <>
                <li className="cursor-pointer hover:bg-[#CFCFCF] hover:text-black px-2.5 rounded-3xl">
                  <Link href="/upload">Upload</Link>
                </li>
              </>
            )}
            <li className="cursor-pointer hover:bg-[#CFCFCF] hover:text-black px-2.5 rounded-3xl">
              <Link href="/about">About</Link>
            </li>
          </div>
        </ul>
      </nav>
    </header>
  );
};
export default NavBar;
