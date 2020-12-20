package com.example.mobprogramming.solution;

public class ObjectsWithoutEngines extends TransportationObject {
    boolean forceApplied;

    public boolean isForceApplied() {
        return forceApplied;
    }

    public void setForceApplied(boolean forceApplied) {
        this.forceApplied = forceApplied;
    }

    public String applyForce(boolean forceApplied) {
        String message;
        if(forceApplied == true){
            message = "Start moving...";
        }
        else{
            message = "Can't move. Apply more force.";
        }
        return message;
    }
}
