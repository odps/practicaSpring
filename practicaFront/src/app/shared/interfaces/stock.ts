import {Shop} from './shop';
import {Product} from './product';
import {Order} from './order';

export interface Stock {
  stockPk: number;
  stockOwner: Shop;
  stockProduct: Product;
  stockQuantity: number;
  createdAt: Date;
  updatedAt: Date;
  orders: Order[];

}
