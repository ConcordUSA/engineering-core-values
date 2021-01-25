import {Product} from './product'

export class SimpleProductDao {
    private products: Map<number, Product>

    constructor(products: Map<number, Product>){
        this.products = products
    }

    findById(id: number): Product | undefined{
        return this.products.get(id)
    }

    findAll(): Product[] {
        return Array.from(this.products.values())
    }

}