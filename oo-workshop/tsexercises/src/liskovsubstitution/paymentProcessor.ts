import {CreditCard} from './creditCard'
import {DebitCard} from './debitCard'
import {RewardsPoints} from './rewardsPoints'


export class PaymentProcessor {
    validCardNumber: boolean | undefined
    creditCard: CreditCard = new CreditCard()
    debitCard: DebitCard = new DebitCard()
    rewardsPoints: RewardsPoints = new RewardsPoints()

    public validate(validCardNumber: boolean): void{
        this.creditCard.validateCardPayment(validCardNumber)
        this.debitCard.validateCardPayment(validCardNumber)
        this.rewardsPoints.validateCardPayment(validCardNumber) // validation for using rewards points works
        // differently than validating card payments!
    }
}
