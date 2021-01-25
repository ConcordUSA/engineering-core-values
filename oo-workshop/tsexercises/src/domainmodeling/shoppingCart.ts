import { Product } from "./product"

import {Customer} from './customer'

export class ShoppingCart {
    id: string
    items: Product[]
    customer: Customer

    constructor(id: string, items: Product[], customer: Customer){
        this.id = id
        this.items = items
        this.customer = customer
    }
}