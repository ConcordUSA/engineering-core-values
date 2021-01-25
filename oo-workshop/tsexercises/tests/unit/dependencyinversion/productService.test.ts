import {ProductService} from '../../../src/dependencyinversion/productService'
import {Product} from '../../../src/dependencyinversion/product'
import {SimpleProductDao} from '../../../src/dependencyinversion/simpleProductDao'

describe('product service', () => {
    let tested: ProductService
    let product1: Product
    let product2: Product

    beforeEach(() => {
        product1 = new Product("fence post")
        product2 = new Product("landscape fabric")

        const products: Map<number, Product> = new Map([
            [1, product1],
            [2, product2]
        ])
        const simpleProductDao = new SimpleProductDao(products)
        tested = new ProductService(simpleProductDao)
    })

    it('should find present product by id', () => {
        const actualProduct: Product | undefined = tested.findById(1)
        expect(actualProduct).toEqual(product1)
    })

    it('should find all present products', () => {
        const actualProducts: Product[] = tested.findAll()
        expect(actualProducts).toEqual([product1, product2])
    })
})