import { Category } from "./category";
import { Provider } from "./provider";

export interface Article {
  id: number;
  codArticle: string;
  name: string;
  description?: any;
  price: number;
  image?: any;
  isDeleted?: boolean;
  category: Category;
  provider: Provider;
}
