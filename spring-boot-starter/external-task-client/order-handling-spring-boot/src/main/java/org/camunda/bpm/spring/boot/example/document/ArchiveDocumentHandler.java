package org.camunda.bpm.spring.boot.example.document;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("CamundaTask_ArchiveDocument")
public class ArchiveDocumentHandler implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        // log
        externalTaskService.complete(externalTask);

        // externalTaskService.handleFailure(
        //  task.getId(),
        //  "externalWorkerId",
        //  "Address could not be validated: Address database not reachable",     // errorMessage
        //  "Super long error details",                                           // errorDetails
        //  1,                                                                    // retries
        //  10L * 60L * 1000L);                                                   // retryTimeout

        // externalTaskService.handleBpmnError(
        //  task.getId(),
        //  "externalWorkerId",
        //  "bpmn-error"); //errorCode
    }
}
