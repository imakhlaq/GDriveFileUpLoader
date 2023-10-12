import { fileData } from "@/utils/fileInfo";
import Column from "@/app/_component/Column";

type Props = {};
const Table = ({}: Props) => {
  return (
    <section>
      <div className="grid grid-cols-3 w-screen place-items-center mt-16 gap-1">
        <div className="col-span-3 flex gap-32 md:gap-56 lg:gap-72 xl:gap-96 bg-[#4D5057] py-2 px-7 rounded-3xl mb-4">
          <p className="text-xl font-semibold">Name</p>
          <p className="text-xl font-semibold">Type</p>
          <p className="text-xl font-semibold">Size</p>
        </div>
        {fileData.map((data) => (
          <Column key={data.id} data={data} />
        ))}
      </div>
    </section>
  );
};
export default Table;
