import {Event} from './event'

export class SimpleEventDao {
    private customers: Map<number, Event>

    constructor(customers: Map<number, Event>){
        this.customers = customers
    }

    findById(id: number): Event | undefined{
        return this.customers.get(id)
    }

    findAll(): Event[] {
        return Array.from(this.customers.values())
    }

}