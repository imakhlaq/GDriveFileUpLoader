import axios from "axios";
import {getAuth} from "@/utils/isAuth";

export const service = axios.create({
baseURL: "http://localhost:8080/api",headers:{
    Authorization:`Bearer ${getAuth()?.token}`
  }
});
