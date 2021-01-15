import {Car} from '../../../src/liskovsubstitution/car'
import {Bus} from '../../../src/liskovsubstitution/bus'
import {Bicycle} from '../../../src/liskovsubstitution/bicycle'
import {Skateboard} from '../../../src/liskovsubstitution/skateboard'


describe('transportation object', () => {
 
    it('when battery is charged, engine will start', () => {
        //given
        const car: Car = new Car()
        const bus: Bus = new Bus()

        //when
        const whencar: string = car.startEngine(true)
        const whenbus: string = bus.startEngine(true)

        //then
        expect(whencar).toContain("Starting engine")
        expect(whenbus).toContain("Starting engine")
    })

    it('when battery is not charged, engine will not start', () => {
        //given
        const car: Car = new Car()
        const bus: Bus = new Bus()

        //when
        const whencar: string = car.startEngine(false)
        const whenbus: string = bus.startEngine(false)

        //then
        expect(whencar).toContain("Charge battery")
        expect(whenbus).toContain("Charge battery")
    })

    it('when force applied, it will start moving', () => {
        //given
        const bicycle: Bicycle = new Bicycle()
        const skateboard: Skateboard = new Skateboard()
       
        //when
        const whenbicycle = bicycle.applyForce(true)
        const whenskateboard = skateboard.applyForce(true)

        //then
        expect(whenbicycle).toContain("Start moving")
        expect(whenskateboard).toContain("Start moving")
    })

    it('when force not applied, it will not start moving', () => {
        //given
        const bicycle: Bicycle = new Bicycle()
        const skateboard: Skateboard = new Skateboard()
        
        //when
        const whenbicycle = bicycle.applyForce(false)
        const whenskateboard = skateboard.applyForce(false)
      
        //then
        expect(whenbicycle).toContain("Apply more force")
        expect(whenskateboard).toContain("Apply more force")
    })

})

