class Engine{}

export class TransportationObject {
    public speed: number | undefined;
    public engine: Engine | undefined;
    public batteryCharged: boolean | undefined;
    public forceApplied: boolean | undefined;

    public constructor(){}

    public startEngine(batteryCharged: boolean): string{
        let message: string;
        if(batteryCharged){
            message = "Starting engine...";
        }
        else{
            message = "Can't start engine. Charge battery.";
        }
        return message;
    }

    public applyForce(forceApplied: boolean): string{
        let message: string;
        if(forceApplied){
            message = "Start moving...";
        }
        else{
            message = "Can't move. Apply more force.";
        }
        return message;
    }
}