export interface CustomResponse {

  timeStamp: Date;
  statusCode: number;
  status: string;
  reason: string;
  devMsg: string;
  msg: string;
  data: any[];
}
