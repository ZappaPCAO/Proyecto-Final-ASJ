import { IvaCondition } from "./ivaCondition";
import { Sector } from "./sector";

export interface Provider {
  id: number;
  codProvider: string;
  businessName: string;
  website?: string;
  email: string;  
  phone: string;
  logo: string;
  isDeleted?: boolean;
  location:{
    id: number;
    street: string;
    number?: number;
    postalCode: string;
    city:{
      id: number;
      name: string;
      state:{
        id: number;
        name: string;
        country:{
          id: number;
          name: string;
        }
      }
    }
  };
  sector: Sector;
  taxData:{
    id: number;
    cuit: string;
    ivaCondition: IvaCondition;
  };  
  contactData:{
    id: number;
    name: string;
    lastName: string;
    phone: string;
    email: string;
    role: string;
  }
}