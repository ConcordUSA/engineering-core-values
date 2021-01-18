export class CartItem{
    //todo how do we feel about direct access vs getter/setter?
    id: string
    name: string
    price: number

    constructor(id: string, name: string, price: number){
        this.id = id
        this.name = name
        this.price = price
    }

}