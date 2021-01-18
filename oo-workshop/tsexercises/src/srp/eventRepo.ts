import {Event} from './event'

export interface EventRepo {
    getAllEvents(): Event[]
    saveAllEvents(events: Event[]): boolean
}