import {Shop} from './shop';
import {Product} from './product';
import {Invoice} from './invoice';

export interface Order {
  id: number;
  shop: Shop;
  product: Product;
  quantity: number;
  createdAt: Date;
  updatedAt: Date;
  invoices: Invoice[];
  
}
