import {Product} from './product'

export class SimpleProductDao {
    private products: Map<number, Product>

    constructor(customers: Map<number, Product>){
        this.products = customers
    }

    findById(id: number): Product | undefined{
        return this.products.get(id)
    }

    findAll(): Product[] {
        return Array.from(this.products.values())
    }

}