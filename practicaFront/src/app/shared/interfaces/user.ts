import {Role} from './role';
import {Shop} from './shop';
import {Invoice} from './invoice';

export interface User {
  userId: number;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  role: Role;
  createdAt: Date;
  updatedAt: Date;
  shops: Shop[];
  invoices: Invoice[];
}
