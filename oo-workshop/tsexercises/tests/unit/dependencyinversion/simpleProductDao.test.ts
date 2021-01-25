import {SimpleProductDao} from '../../../src/dependencyinversion/simpleProductDao'
import {Product} from '../../../src/dependencyinversion/product'

describe('simple product dao', () => {
    let tested: SimpleProductDao
    let product1: Product
    let product2: Product

    beforeEach(() => {
        const products: Map<number, Product> = new Map<number, Product>()
        product1 = new Product("gravel")
        product2 = new Product("shovel")
        products.set(1, product1)
        products.set(2, product2)
        tested = new SimpleProductDao(products)
    })

    it('should return a product when given the product id', () => {
        const actualProduct: Product | undefined = tested.findById(1)

        expect(actualProduct).toBe(product1)
    })

    it('should return an array of all products when find all is called', () => {
        const actualProducts: Product[] = tested.findAll()

        expect(actualProducts).toContain(product1)
        expect(actualProducts).toContain(product2)
        expect(actualProducts).toHaveLength(2)
    
    })


    it('should return undefined when given the id of an absent product', () => {
        const actualProduct: Product | undefined = tested.findById(80)

        //assert
        expect(actualProduct).toBeUndefined()
    })
})