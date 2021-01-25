export class PaymentMethod {
    public validCardNumber: boolean | undefined

    public constructor(){}

    public validateCardPayment(validCardNumber: boolean): string{
        let message: string
        if(validCardNumber){
            message = "Payment Validated"
        }
        else{
            message = "Invalid Payment"
        }
        return message
    }
}