import {CreditCard} from '../../../src/liskovsubstitution/creditCard'
import {DebitCard} from '../../../src/liskovsubstitution/debitCard'
import {RewardsPoints} from '../../../src/liskovsubstitution/rewardsPoints'


describe('payment method', () => {
 
    it('when validCardNumber is true, payment is valid', () => {
        //given
        const creditCard: CreditCard = new CreditCard()
        const debitCard: DebitCard = new DebitCard()
        const rewardsPoints: RewardsPoints = new RewardsPoints()

        //when
        const whenCreditCard: string = creditCard.validateCardPayment(true)
        const whenDebitCard: string = debitCard.validateCardPayment(true)
        const whenRewardsPoints: string = rewardsPoints.validateCardPayment(true)

        //then
        expect(whenCreditCard).toContain("Payment Validated")
        expect(whenDebitCard).toContain("Payment Validated")
        expect(whenRewardsPoints).toContain("Payment Validated")
    })

    it('when validCardNumber is false, payment is invalid', () => {
        //given
        const creditCard: CreditCard = new CreditCard()
        const debitCard: DebitCard = new DebitCard()
        const rewardsPoints: RewardsPoints = new RewardsPoints()

        //when
        const whenCreditCard: string = creditCard.validateCardPayment(false)
        const whenDebitCard: string = debitCard.validateCardPayment(false)
        const whenRewardsPoints: string = rewardsPoints.validateCardPayment(false)

        //then
        expect(whenCreditCard).toContain("Invalid Payment")
        expect(whenDebitCard).toContain("Invalid Payment")
        expect(whenRewardsPoints).toContain("Invalid Payment")
    })

})

