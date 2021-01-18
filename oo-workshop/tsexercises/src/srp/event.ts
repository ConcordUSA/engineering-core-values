export class Event{
    public eventName: string
    public eventDate: string
    public eventCost: string
    public eventLocation: string

    public constructor(eventName: string, eventDate: string, eventCost: string, eventLocation: string){
        this.eventName = eventName
        this.eventDate = eventDate
        this.eventCost = eventCost
        this.eventLocation = eventLocation
    }
}