import {Role} from './role';

export interface Permission {
  id: number;
  type: string;
  roles: Role[];
}
