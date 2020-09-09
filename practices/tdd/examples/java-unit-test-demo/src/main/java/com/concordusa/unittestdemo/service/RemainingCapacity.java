package com.concordusa.unittestdemo.service;

public class RemainingCapacity {
    private final MutableInteger remainingCapacity;

    RemainingCapacity(final MutableInteger spaceCapacity){
        remainingCapacity = spaceCapacity;
    }

    public Integer getRemainingCapacity(){
        return remainingCapacity.getCurrentValue();
    }

    public boolean allowEntry(final Integer partySize){
        return remainingCapacity.getCurrentValue() >= partySize;
    }

    public void admitParty(final Integer partySize){
        remainingCapacity.subtract(partySize);
    }

    public void removeParty(final Integer partySize){
        remainingCapacity.add(partySize);
    }

    public boolean admitPartyIfAllowedEntry(final Integer partySize){
        if(!allowEntry(partySize)){
            return false;
        }
        admitParty(partySize);
        return true;
    }

    public void enforceCapacity_a(){
        if(remainingCapacity.getCurrentValue() >= 0){
            return;
        }

        remainingCapacity.subtract(remainingCapacity.getCurrentValue());
    }

    public void enforceCapacity_b(){
        if(remainingCapacity.getCurrentValue() < 0){
            remainingCapacity.add(-1 * remainingCapacity.getCurrentValue());
        }
    }
}
