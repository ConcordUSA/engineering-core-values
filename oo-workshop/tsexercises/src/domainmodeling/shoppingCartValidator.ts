import {ShoppingCart} from './shoppingCart'
import {InvalidCustomerError} from './invalidCustomerError'

export class ShoppingCartValidator {

    public static IsShoppingCartValid(shoppingCart: ShoppingCart): boolean | InvalidCustomerError {
        if (shoppingCart.customer == null) {
            return new InvalidCustomerError()
        }
        return true
    }
}