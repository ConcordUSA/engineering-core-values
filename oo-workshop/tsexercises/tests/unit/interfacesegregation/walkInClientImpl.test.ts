import {WalkInClientImpl} from '../../../src/interfacesegregation/walkInClientImpl'

describe('walk in client implementation', () => {
 
    it('should accept a walk in order', () => {
        //arrange
        const given: WalkInClientImpl = new WalkInClientImpl("Roger Miller", "sand", "9.99")

        //act
        const when: string = given.walkInCustomerOrder()

        //assert
        expect(when).toContain("Roger Miller")
        expect(when).toContain("sand")
    })


    it('should accept in person payment', () => {
        //arrange
        const given: WalkInClientImpl = new WalkInClientImpl("Roger Miller", "sand", "9.99")

        //act
        const when: string = given.payInPerson()

        //assert
        expect(when).toContain("9.99")
    })
})

