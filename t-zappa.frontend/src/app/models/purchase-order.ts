import { Detail } from "./detail";
import { Provider } from "./provider";

export interface PurchaseOrder {
  id: number;
  numPurchaseOrder: string;
  sendDate: string;
  receiptDate: string;
  email: string;
  description: string;
  details: Detail[];
  state: 'A' | 'C';
  total: number;
  provider: Provider;
}