"use client";
import usePrivateRoute from "@/hooks/usePrivateRoute";
import { useEffect } from "react";
import dynamic from "next/dynamic";
const Upload = dynamic(
    () => import('../_component/Upload'),
    { ssr: false }
)

const Page = () => {
  const protectedFn = usePrivateRoute();

  useEffect(() => {
    protectedFn();
  }, []);

  return <Upload />;
};
export default Page;
