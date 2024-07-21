// user.ts
export interface User {
  userId: number;
  userName: string;
  email: string;
  address: string;
  password: string;
  accounts : any[];
}

// login-dto.ts
export interface LoginDto {
  userName: string;
  password: string;
}

// register-dto.ts
export interface RegisterDto {
  userName: string;
  email: string;
  address: string;
  password: string;
}
