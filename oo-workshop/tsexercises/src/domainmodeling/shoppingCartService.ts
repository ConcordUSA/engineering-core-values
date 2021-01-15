import {CartItem} from './cartItem'
import {ShoppingCart} from './shoppingCart'
import {ShoppingCartRepository} from './shoppingCartRepository'

export class ShoppingCartService{

    private repository: ShoppingCartRepository;

    public constructor(repository: ShoppingCartRepository){
        this.repository = repository;
    }

    public addItemToShoppingCart(shoppingCartId: string, item: CartItem): ShoppingCart {
        let shoppingCart: ShoppingCart = this.repository.find(shoppingCartId);

        shoppingCart.items.push(item);

        this.repository.save(shoppingCart);

        return shoppingCart;
    }

    public removeItemFromShoppingCart(shoppingCartId: string, itemId: string): ShoppingCart{
        let shoppingCart: ShoppingCart = this.repository.find(shoppingCartId);

        let deletionIndex: number = -1;
        shoppingCart.items.forEach((item: CartItem, itemIndex) => {
            if(item.id === itemId){
                deletionIndex = itemIndex;
            }
        })

        //the arbitrary looking '1' is saying how many items to remove starting at the deletion index
        shoppingCart.items.splice(deletionIndex, 1);
   
        return this.repository.save(shoppingCart);

    }

    public calculateShoppingCartTotal(shoppingCartId: string): number {
        let shoppingCart: ShoppingCart = this.repository.find(shoppingCartId);

        var total: number = 0;
        shoppingCart.items.forEach((item: CartItem) => {
            total = total + item.price
        })
    
        return total;
    }

    public getItemsInCart(shoppingCartId: string): CartItem[] {
        var shoppingCart: ShoppingCart = this.repository.find(shoppingCartId);

        //todo this previously sorted the list by price, not sure if that's needed for this example
        var cartItems: CartItem[] = shoppingCart.items;

        return cartItems;

    }
}