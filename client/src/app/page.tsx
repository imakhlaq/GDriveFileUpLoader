import Table from "@/app/_component/Table";
import { service } from "@/utils/service";
import NoFiles from "@/app/_component/NoFiles";

export const dynamic = "force-dynamic";
type Props = {};

async function getFileInfo() {
  try {
    return await service.get<MyFile[]>("/myfiles");
  } catch (e) {
    console.log(e);
  }
}

export default async function Page({}: Props) {
  const files = await getFileInfo();

  console.log(files)

  if (!files) return <NoFiles />;

  //fetch data
  return <Table files={files.data} />;
}
