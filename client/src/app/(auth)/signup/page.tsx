"use client";
import { useForm } from "react-hook-form";
import { useAuth } from "@/hooks/useAuth";

type FormValues = {
  username: string;
  email: string;
  password: string;
};

export default function Page() {
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm<FormValues>();
  const authFun = useAuth();

  const onSubmit = handleSubmit((data) => {
    authFun({
      type: "signup",
      data,
    })
      .then((res) => {
        console.log(res);
        localStorage.setItem("gdriveToken", JSON.stringify(res.data));
      })
      .catch((e) => {
        console.log(e);
        const message = e.response.data.message;
        if (typeof message !== "string") return;
        if (message.includes("Username"))
          setError("username", { message: "Username is taken" });
        if (message.includes("Email"))
          setError("email", { message: "Email is registered" });
        if (message.includes("Password"))
          setError("username", { message: "Password is weak" });
      });
  });

  return (
    <section className="container relative mx-auto my-80 flex max-w-sm md:max-w-sm lg:max-w-md flex-col px-6 justify-center gap-16 bg-[#939196] py-10 rounded-md text-gray-950">
      <h2 className="font-semibold text-3xl">Login</h2>
      <form
        className="flex flex-col justify-center gap-4 text-lg font-semibold"
        onSubmit={onSubmit}
      >
        {errors.username && (
          <p className="text-red-800">{errors.username.message}</p>
        )}
        <div className="flex flex-col">
          <label htmlFor="username" className="mb-2 text-xl">
            Username
          </label>
          <input
            {...register("username")}
            type="text"
            id="username"
            className="rounded-sm outline-none py-0.5 px-1 shadow-md focus:shadow-xl "
          />
        </div>
        {errors.password && (
          <p className="text-red-800">{errors.password.message}</p>
        )}
        <div className="flex flex-col">
          <label htmlFor="email" className="mb-2 text-xl">
            Email
          </label>
          <input
            {...register("email")}
            type="email"
            id="email"
            className="rounded-sm outline-none py-0.5 px-1 shadow-md focus:shadow-xl "
          />
        </div>
        {errors.password && (
          <p className="text-red-800">{errors.password.message}</p>
        )}
        <div className="flex flex-col">
          <label htmlFor="password" className="mb-2 text-xl">
            Password
          </label>
          <input
            {...register("password")}
            type="password"
            id="password"
            className="rounded-sm outline-none py-0.5 px-1 shadow-md focus:shadow-xl "
          />
        </div>
        <button
          className="rounded-md bg-[#CFCFCF] mt-4 py-2 w-36 mx-auto shadow-md hover:shadow-xl hover:bg-white"
          type="submit"
        >
          Submit
        </button>
      </form>
    </section>
  );
}
