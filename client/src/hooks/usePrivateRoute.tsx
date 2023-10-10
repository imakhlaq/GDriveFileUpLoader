"use client";
import { getAuth } from "@/utils/isAuth";
import { useRouter } from "next/navigation";

export default function usePrivateRoute() {
  const router = useRouter();

  return function () {
    const isAuth = getAuth();
    if (!isAuth) router.push("/login");
  };
}
