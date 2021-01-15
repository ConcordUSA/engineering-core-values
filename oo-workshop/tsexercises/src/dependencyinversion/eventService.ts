import {Event} from './event'
import {SimpleEventDao} from './simpleEventDao'

export class EventService {
    private simpleCustomerDao: SimpleEventDao;

    public constructor(simpleCustomerDao: SimpleEventDao){
        this.simpleCustomerDao = simpleCustomerDao;
    }

    public findById(id: number): Event | undefined{
        return this.simpleCustomerDao.findById(id);
    }

    public findAll(): Event[]{
        return this.simpleCustomerDao.findAll();
    }

}