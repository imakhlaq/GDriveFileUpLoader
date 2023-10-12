import Link from "next/link";

type Props = {
  data: {
    id: string;
    name: string;
    contentType: string;
    size: string;
  };
};

const Column = (props: Props) => {
  return (
    <Link
      href={"/"}
      className="col-span-3 flex gap-32 md:gap-56 lg:gap-72 xl:gap-96 hover:bg-[#CFCFCF] hover:rounded-3xl py-2 px-7 rounded-3xl cursor-pointer hover:text-black font-medium"
    >
      <p>{props.data.name}</p>
      <p>{props.data.contentType}</p>
      <p>{props.data.size}</p>
    </Link>
  );
};
export default Column;
