package org.camunda.bpm.spring.boot.example;


public class ServiceRequestToDo {
    public String getServiceRequestToDoId() {
        return serviceRequestToDoId;
    }

    public void setServiceRequestToDoId(String serviceRequestToDoId) {
        this.serviceRequestToDoId = serviceRequestToDoId;
    }

    public String getServiceRequestToDoStatus() {
        return serviceRequestToDoStatus;
    }

    public void setServiceRequestToDoStatus(String serviceRequestToDoStatus) {
        this.serviceRequestToDoStatus = serviceRequestToDoStatus;
    }

    private String serviceRequestToDoId;
    private String serviceRequestToDoStatus;

}
