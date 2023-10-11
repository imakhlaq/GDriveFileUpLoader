import { service } from "@/utils/service";

type LoginData = {
  username: string;
  password: string;
};
type SignupData = LoginData & {
  email: string;
};

type Props = {
  type: "login" | "signup";
  data: LoginData | SignupData;
};

export function useAuth() {
  return async function ({ type, data }: Props) {
    await service.post<AuthRes>(`/auth/${type}`, data);
  };
}
