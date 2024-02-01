import { Category } from "./category";
import { Provider } from "./provider";

export interface Article {
  id: number;
  codArticle: string;
  name: string;
  description?: string;
  price: number;
  image: string;
  isDeleted?: boolean;
  category: Category;
  provider: Provider;
}
