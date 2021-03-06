import {OnlineClientImpl} from '../../../src/interfacesegregation/onlineClientImpl'

describe('online client implementation', () => {
 
    it('should accept an online order', () => {
        //arrange
        const given: OnlineClientImpl = new OnlineClientImpl("Brandon Gilbert", "drain tile", "6.99")

        //act
        const when: string = given.acceptOnlineOrder()

        //assert
        expect(when).toContain("Brandon Gilbert")
        expect(when).toContain("drain tile")
    })


    it('should accept online payment', () => {
        //arrange
        const given: OnlineClientImpl = new OnlineClientImpl("Brandon Gilbert", "drain tile", "6.99")

        //act
        const when: string = given.payOnline()

        //assert
        expect(when).toContain("6.99")
    })
})

