export type User = {
  id: number,
  name: string,
  email: string
};

export type Message = {
  id: number,
  message: string,
  from_user_id: number
  // chat_id: number;
  createdAt: Array<number>,
  updatedAt: Array<number>
};

export type Chat = {
  id: number,
  name: string,
  messages: Array<Message>,
  createdAt: Array<number>,
  updatedAt: Array<number>,
};

export type UserChat={
  id:number,
  user_id:number,
  chat:Chat
}

export type Nullable<T> = T | any;

