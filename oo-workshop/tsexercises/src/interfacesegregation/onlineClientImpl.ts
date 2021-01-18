import { RestaurantInterface } from "./restaurantInterface"

export class OnlineClientImpl implements RestaurantInterface {
    public name: string
    public food: string
    public price: string

    public constructor(name: string, food: string, price: string){
        this.name = name
        this.food = food
        this.price = price
    }

    public acceptOnlineOrder(): string {
        const message: string = "Online order from " + this.name + " for " + this.food
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