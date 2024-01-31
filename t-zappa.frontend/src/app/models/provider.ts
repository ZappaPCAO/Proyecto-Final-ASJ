import { obtenerObjetoByTipo } from "../utils/localStorage";

export interface Provider {
  id: number;
  codProvider: string;
  businessName: string;
  website?: string;
  email: string;  
  phone: string;
  logo: string;
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
  sector:{
    id: number;
    sector: string;
  }
  taxData:{
    id: number;
    cuit: string;
    ivaCondition: {
      id: number;
      condition: string;
    }
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