package com.dr3amers.exception;

import com.dr3amers.model.enumerated.Status;

public class InvalidStatusUpdateException extends RuntimeException {

    public InvalidStatusUpdateException(Status updatedStatus, Status initialStatus) {
        super("You can't reach status "+ updatedStatus.name()+ " from "+ initialStatus.name());
    }

    public InvalidStatusUpdateException(String msg) {
        super(msg);
    }

    public String toString(){
        return this.getMessage() +"\n" + getClass();
    }
}
