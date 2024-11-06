import { Cart } from "../../../../../features/cart/models/cart.model";

export const MOCK_CART: Cart = {
    cartItems: [
        {
            product: {
                id: 1,
                name: "Wireless Headphones",
                imageURL: "https://example.com/images/headphones.jpg",
                price: 99.99,
                description: "High-quality wireless headphones with noise cancellation.",
                categoryId: 1
            },
            quantity: 2
        },
        {
            product: {
                id: 3,
                name: "Laptop",
                imageURL: "https://example.com/images/laptop.jpg",
                price: 899.99,
                description: "Lightweight laptop with powerful performance.",
                categoryId: 3
            },
            quantity: 1
        },
        {
            product: {
                id: 4,
                name: "Bluetooth Speaker",
                imageURL: "https://example.com/images/speaker.jpg",
                price: 49.99,
                description: "Portable Bluetooth speaker with rich sound.",
                categoryId: 4
            },
            quantity: 3
        }
    ],
    total: 1199.95 // This can be calculated based on the cart items
};
