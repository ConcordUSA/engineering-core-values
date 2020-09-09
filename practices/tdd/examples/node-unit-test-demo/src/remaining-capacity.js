class RemainingCapacity {

    constructor(spaceCapacity){
        this.remainingCapacity = spaceCapacity;
    }

    getRemainingCapacity(){
        return this.remainingCapacity.getCurrentValue();
    }

    allowEntry(partySize){
        return this.remainingCapacity.getCurrentValue() >= partySize;
    }

    admitParty(partySize){
        this.remainingCapacity.subtract(partySize);
    }

    removeParty(partySize){
        this.remainingCapacity.add(partySize);
    }

    admitPartyIfAllowedEntry(partySize){
        if(!this.allowEntry(partySize)){
            return false;
        }
        this.admitParty(partySize);
        return true;
    }

    enforceCapacity_a(){
        if(this.remainingCapacity.getCurrentValue() >= 0){
            return;
        }

        this.remainingCapacity.subtract(this.remainingCapacity.getCurrentValue());
    }

    enforceCapacity_b(){
        if(this.remainingCapacity.getCurrentValue() < 0){
            this.remainingCapacity.add(-1 * this.remainingCapacity.getCurrentValue());
        }
    }
}

exports.default = RemainingCapacity