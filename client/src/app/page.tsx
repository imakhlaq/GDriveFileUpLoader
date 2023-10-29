"use client";
import Table from "@/app/_component/Table";
import { service } from "@/utils/service";
import NoFiles from "@/app/_component/NoFiles";
import { useEffect, useState } from "react";
import { getAuth } from "@/utils/isAuth";

export default function Page() {
  const [files, setFiles] = useState<MyFile[]>([]);

  useEffect(() => {
    async function getFiles() {
      const data = getAuth();

      const token = data?.token;

      return await service.get<MyFile[]>("/myfiles", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    const data = getFiles()
      .then((res) => setFiles(res.data))
      .catch((e) => console.log(e));
  }, []);

  console.log(files);

  if (files.length === 0) return <NoFiles />;

  //fetch data
  return <Table files={files} />;
}
