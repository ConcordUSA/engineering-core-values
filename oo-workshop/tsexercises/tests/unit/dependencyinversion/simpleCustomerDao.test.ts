import {SimpleEventDao} from '../../../src/dependencyinversion/simpleEventDao'
import {Event} from '../../../src/dependencyinversion/event'

describe('simple event dao', () => {
    let simpleEventDao: SimpleEventDao
    let event1: Event
    let event2: Event

    beforeEach(() => {
        const customers: Map<number, Event> = new Map<>()
        event1 = new Event("birthday party")
        event2 = new Event("happy hour")
        customers.set(1, event1)
        customers.set(2, event2)
        simpleEventDao = new SimpleEventDao(customers)
    })

    it('should return an event when given the event id', () => {
        const actualEvent: Event | undefined = simpleEventDao.findById(1)

        expect(actualEvent).toBe(event1)
    })

    it('should return an array of all events when find all is called', () => {
        const actualEvents: Event[] = simpleEventDao.findAll()

        expect(actualEvents).toContain(event1)
        expect(actualEvents).toContain(event2)
        expect(actualEvents).toHaveLength(2)
    
    })


    it('should return undefined when given the id of an absent event', () => {
        const actualEvent: Event | undefined = simpleEventDao.findById(80)

        //assert
        expect(actualEvent).toBeUndefined()
    })
})