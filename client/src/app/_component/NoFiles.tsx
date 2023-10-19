"use client";
import { useEffect } from "react";
import usePrivateRoute from "@/hooks/usePrivateRoute";

export default function NoFiles() {
  const protectedFun = usePrivateRoute();

  useEffect(() => {
    protectedFun();
  }, []);

  return (
    <div className="h-screen w-screen flex justify-center items-center">
      <p className="text-6xl font-medium text-gray-400">No Files Available</p>
    </div>
  );
}
