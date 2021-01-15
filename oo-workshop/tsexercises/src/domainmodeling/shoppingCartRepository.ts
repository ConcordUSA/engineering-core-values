import { ShoppingCart } from "./shoppingCart";

export interface ShoppingCartRepository{
    save(shoppingCart: ShoppingCart): ShoppingCart;
    find(shoppingCartId: string): ShoppingCart;
}