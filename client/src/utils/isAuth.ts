export const getAuth = () => {

  if (typeof window !== "undefined"){
    const data=localStorage.getItem("gdriveToken");
    if (!data)return null;
    return JSON.parse(data) as AuthRes;
  }
  return null;
};
