import { CartItem } from "./cartItem"

import {Customer} from './customer'

export class ShoppingCart {
    id: string
    items: CartItem[]
    customer: Customer

    constructor(id: string, items: CartItem[], customer: Customer){
        this.id = id
        this.items = items
        this.customer = customer
    }
}