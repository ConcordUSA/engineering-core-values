import { StoreInterface } from "./storeInterface"

export class OnlineClientImpl implements StoreInterface {
    //a more complete solution may use the shopping cart from the domain modeling exercise
    public customer: string
    public product: string
    public price: string

    public constructor(name: string, food: string, price: string){
        this.customer = name
        this.product = food
        this.price = price
    }

    public acceptOnlineOrder(): string {
        const message: string = "Online order from " + this.customer + " for " + this.product
        return message
    }

    public payOnline(): string {
        const message: string = "Online payment for $" + this.price + " processed"
        return message
    }

    public walkInCustomerOrder(): string {
        //not applicable for online order
        throw new Error("not supported")
    }

    public payInPerson(): string {
        //not applicable for online order
        throw new Error("not supported")
    }
}