import { Article } from "./article";

export interface Detail{
    id?: number;
    article: Article;
    amount: number;
    subtotal: number;
}