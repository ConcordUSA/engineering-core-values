import {Event} from './event'
import {EventRepo} from './eventRepo'

export class EventApplication{
    private eventRepo: EventRepo

    public constructor(eventRepo: EventRepo){
        this.eventRepo = eventRepo
    }

    public scheduleEvent(eventName: string, eventDate: string, eventCost: string, eventLocation: string): boolean{
        const validEvent: boolean = this.validEventName(eventName) && 
                                    this.validEventDate(eventDate) && 
                                    this.validEventLocation(eventLocation) && 
                                    this.validateEventCost(eventCost)
        if(!validEvent) {
            return false
        }
        const newEvent = new Event(eventName, eventDate, eventCost, eventLocation)
        const existingEvents: Event[] = this.eventRepo.getAllEvents()
        if(this.existingEventsContainsNewEvent(existingEvents, newEvent)){
            return false
        }
        existingEvents.push(newEvent)
        
        return this.eventRepo.saveAllEvents(existingEvents)

    }

    public updateNamedEvent(eventName: string, eventDate: string, eventCost: string, eventLocation: string): boolean{
        const validEvent: boolean = this.validEventName(eventName) && 
                                    this.validEventDate(eventDate) && 
                                    this.validEventLocation(eventLocation) && 
                                    this.validateEventCost(eventCost)
        if(!validEvent) {
            return false
        }
        const newEvent = new Event(eventName, eventDate, eventCost, eventLocation)
        const existingEvents: Event[] = this.eventRepo.getAllEvents()
        
        if(this.replaceExistingEventWithNewEvent(existingEvents, newEvent)){
            return this.eventRepo.saveAllEvents(existingEvents)
        }

        return false
    }

    private existingEventsContainsNewEvent(existingEvents: Event[], newEvent: Event): boolean{
        let alreadyExists: boolean = false

        existingEvents.forEach((existingEvent) => {
            if(existingEvent.eventName === newEvent.eventName &&
                existingEvent.eventDate === newEvent.eventDate &&
                existingEvent.eventCost === newEvent.eventCost &&
                existingEvent.eventLocation === newEvent.eventLocation)
                alreadyExists = true
        })

        return alreadyExists
    }

    private replaceExistingEventWithNewEvent(existingEvents: Event[], newEvent: Event): boolean{
        let existingIndex: number = -1

        existingEvents.forEach((existingEvent, index) => {
            if(existingEvent.eventName === newEvent.eventName){
                existingIndex = index
            }
        })
        if(existingIndex < 0){
            return false
        }
        existingEvents[existingIndex] = newEvent

        return true
    }

    private validEventName(eventName: string): boolean{
        return eventName.length > 10 // A nice long name makes an event seem important
    }
    private validEventDate(eventDate: string): boolean{
        return eventDate.length === 7 // MM/YYYY
    }
    private validEventLocation(eventLocation: string): boolean{
        return eventLocation.startsWith("online") //it's a pandemic, no in person events
    }
    private validateEventCost(eventCost: string): boolean{
        try{
            parseInt(eventCost)
            return true
        } catch(error: any){
            return false
        }
    }
}