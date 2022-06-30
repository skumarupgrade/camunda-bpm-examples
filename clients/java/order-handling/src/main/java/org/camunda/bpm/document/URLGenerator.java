package org.camunda.bpm.document;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.FileValue;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
@ExternalTaskSubscription("CamundaTask_URL_Generator")
public class URLGenerator  implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        FileValue typedFileValue = Variables
                .fileValue("addresses.txt")
                .file(new File("imone.jpg"))
                .mimeType("image/jpeg")
                //.encoding("UTF-8")
                .create();
        Map<String, Object> variables = new HashMap<>();
        variables.put("imageToReview",typedFileValue);
        externalTaskService.setVariables(externalTask, variables);

        externalTaskService.complete(externalTask);
    }
}
