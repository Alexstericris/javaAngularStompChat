export type User = {
  id: number,
  username: string,
  email: string
};

export type Message = {
  id: number,
  message: string,
  from_user_id: number
  chat_id: number;
  createdAt: string,
  updatedAt: string
};

export type Chat = {
  id: number,
  name: string,
  from_user_id: number,
  users: Array<number>,
  messages: Array<Message>
};
