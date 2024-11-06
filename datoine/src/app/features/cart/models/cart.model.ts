import { CartItem } from "./cartItem.model";

export interface Cart {
    cartItems: CartItem[],
    total: number
}