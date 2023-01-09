export interface SimpleResponse {

  timeStamp: Date;
  statusCode: number;
  status: string;
  reason: string;
  devMsg: string;
  msg: string;
  data: any;
}
