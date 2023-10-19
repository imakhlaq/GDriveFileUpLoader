export const getAuth = () => {
  return typeof window !== "undefined"
    ? localStorage.getItem("gdriveToken")
    : "";
};
