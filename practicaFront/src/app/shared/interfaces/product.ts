import {Stock} from './stock';
import {Order} from './order';

export interface Product {
  productId: number;
  productName: string;
  productDescription: string;
  productPrice: number;
  createdAt: Date;
  updatedAt: Date;
  stock: Stock[];
  order: Order[];
}
