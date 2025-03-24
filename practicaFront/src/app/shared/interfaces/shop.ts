import {User} from './user';
import {Stock} from './stock';
import {Order} from './order';

export interface Shop {
  shopId: number;
  shopName: string;
  shopOwner: User;
  shopAdress: string;
  shopEmail: string;
  shopPhone: string;
  createdAt: Date;
  updatedAt: Date;
  stocks: Stock[];
  orders: Order[];
}
