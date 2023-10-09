"use client";
import Upload from "@/app/_component/Upload";
import usePrivateRoute from "@/hooks/usePrivateRoute";
import { useEffect } from "react";

export default function Home() {
  const protectedFn = usePrivateRoute();

  useEffect(() => {
    protectedFn();
  }, []);

  return <Upload />;
}
