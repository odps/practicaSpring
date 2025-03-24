import {User} from './user';
import {Order} from './order';

export interface Invoice {
  invoicePk: number;
  user: User;
  order: Order;
  invoiceTotal: number;
  createdAt: Date;
  updatedAt: Date;
}
