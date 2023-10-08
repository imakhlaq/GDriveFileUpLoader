"use client";
import { ChangeEvent, FormEvent, useState } from "react";
import { service } from "@/app/utils/service";

type Props = {};
export default function Upload({}: Props) {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [picked, setPicked] = useState(false);

  const changeHandler = (e: ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files?.item(0)) return;
    setSelectedFile(e.target.files.item(0));
    console.log(e.target.files.item(0));
    setPicked(true);
    console.log(selectedFile);
  };

  const submitHandler = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData: any = new FormData();
    formData.append("file", selectedFile);

    const res = await service.post("/upload", formData);

    console.log(res);
  };

  return (
    <>
      <form
        className="flex justify-center items-center h-screen -mt-16"
        onSubmit={submitHandler}
      >
        <input
          onChange={changeHandler}
          className=""
          type="file"
          name="file"
          accept="image/jpeg, video/mp4"
        />
        <button className="" type="submit">
          Upload
        </button>
      </form>
      {/* {picked && <p>{selectedFile?.name}</p>}
        {picked && <p>{selectedFile?.size}</p>}*/}
    </>
  );
}
