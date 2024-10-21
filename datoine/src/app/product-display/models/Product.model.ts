export interface Product {
    id: number;
    name: string;
    imageURL: string;
    price: number;
    description: string;
    categoryId: number
    [key: string]: string | number;
}