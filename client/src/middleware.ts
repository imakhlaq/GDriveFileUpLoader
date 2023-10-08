import { NextResponse } from "next/server";
import { getAuth } from "@/utils/isAuthenticated";

export function middleware(req: Request) {
  const currentPath = req.url;

  const isAuth = getAuth();

  if (currentPath == "/" && isAuth != null) {
    NextResponse.redirect(new URL("/login"));
  }
}

export const config = {
  matcher: "/",
};

//NextResponse.next()==> for go forward
