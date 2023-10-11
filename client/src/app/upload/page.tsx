"use client";
import usePrivateRoute from "@/hooks/usePrivateRoute";
import { useEffect } from "react";
import Upload from "@/app/_component/Upload";

const Page = () => {
  const protectedFn = usePrivateRoute();

  useEffect(() => {
    protectedFn();
  }, []);

  return <Upload />;
};
export default Page;
