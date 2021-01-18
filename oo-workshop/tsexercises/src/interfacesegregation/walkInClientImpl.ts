import { format } from "path"
import {RestaurantInterface} from './restaurantInterface'

export class WalkInClientImpl implements RestaurantInterface{
    public name: string
    public food: string
    public price: string

    public constructor(name: string, food: string, price: string){
        this.name = name
        this.food = food
        this.price = price
    }

    public acceptOnlineOrder() : string{
        //not applicable for walk-in client
        throw new Error("not supported")
    }
    
    public payOnline(): string{
        //not applicable for walk-in client
        throw new Error("not supported")
    }
    
    public walkInCustomerOrder(): string{
        let message = "Walk-in order from " + this.name + " for " + this.food
        return message
    }

    public payInPerson(): string{
        let message = "In-person payment for $" + this.price + " processed"
        return message
    }
}
