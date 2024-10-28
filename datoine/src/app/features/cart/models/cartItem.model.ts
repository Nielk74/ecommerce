import { Product } from "../../product-display/models/Product.model";

export interface CartItem {
    product: Product
    quantity: number
}