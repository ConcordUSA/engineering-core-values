import {Product} from './product'
import {SimpleProductDao} from './simpleProductDao'

export class ProductService {
    private simpleProductDao: SimpleProductDao

    public constructor(simpleCustomerDao: SimpleProductDao){
        this.simpleProductDao = simpleCustomerDao
    }

    public findById(id: number): Product | undefined{
        return this.simpleProductDao.findById(id)
    }

    public findAll(): Product[]{
        return this.simpleProductDao.findAll()
    }

}