import { service } from "@/utils/service";
import { Simulate } from "react-dom/test-utils";
import click = Simulate.click;

type Props = {
  data: MyFile;
};

async function fetchFile(data: MyFile) {
  console.log("clicked");
  const res = await service.post(
    "/download",
    { id: data.id, fileId: data.url },
    {
      responseType: "blob",
    },
  );

  const url = window.URL.createObjectURL(new Blob([res.data]));
  const link = document.createElement("a");
  link.href = url;
  link.setAttribute("download", `${data.fileName}`);
  document.body.appendChild(link);
  link.click();

  // Clean up and remove the link
  link.parentNode?.removeChild(link);
  window.URL.revokeObjectURL(url);
}

const Column = (props: Props) => {
  return (
    <div
      className="col-span-3 flex gap-32 md:gap-56 lg:gap-72 xl:gap-96 hover:bg-[#CFCFCF] hover:rounded-3xl py-2 px-7 rounded-3xl cursor-pointer hover:text-black font-medium"
      onClick={() => fetchFile(props.data)}
    >
      <p>{props.data.fileName}</p>
      <p>{props.data.contentType}</p>
      <p>{props.data.size} kb</p>
    </div>
  );
};
export default Column;
