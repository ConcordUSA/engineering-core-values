import {Car} from './car'
import {Bus} from './bus'
import {Bicycle} from './bicycle'
import {Skateboard} from './skateboard'


export class Move {
    batteryCharged: boolean | undefined;
    car: Car = new Car();
    bus: Bus = new Bus();
    bicycle: Bicycle = new Bicycle();
    skateboard: Skateboard = new Skateboard();

    public startMoving(batteryCharged: boolean): void{
        this.car.startEngine(batteryCharged);
        this.bus.startEngine(batteryCharged);
        this.bicycle.startEngine(batteryCharged); // there's no engine to start!
        this.skateboard.startEngine(batteryCharged); // there's no engine to start!
    }
}
