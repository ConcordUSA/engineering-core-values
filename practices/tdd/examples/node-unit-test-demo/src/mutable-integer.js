class MutableInteger{
    constructor(initialValue){
        this.currentValue = initialValue;
    }

    getCurrentValue() {
        return this.currentValue;
    }

    add(increment) {
        this.currentValue = this.currentValue + increment;
    }

    subtract(decrement){
        this.currentValue = this.currentValue - decrement;
    }
}

exports.default = MutableInteger