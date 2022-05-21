package org.camunda.bpm.spring.boot.example;

import okhttp3.OkHttpClient;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.api.TaskApi;
import org.camunda.community.rest.client.dto.StartProcessInstanceDto;
import org.camunda.community.rest.client.dto.VariableValueDto;
import org.camunda.community.rest.client.invoker.ApiClient;
import org.camunda.community.rest.client.invoker.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CamundaTaskClient {
    @Autowired
    private TaskApi taskApi;

    @Autowired
    private ProcessDefinitionApi processDefinitionApi;

    public void startProcess() throws ApiException {
        ApiClient client = new ApiClient();
        //client.setBasePath("https://activity-srvc-skumar.actuator.kube.usw2.ondemand.upgrade.com/api/activity/engine-rest");

        StartProcessInstanceDto startProcessInstanceDto = new StartProcessInstanceDto();
        startProcessInstanceDto.businessKey("serviceRequestId1");
        Map<String, VariableValueDto> variables = new HashMap<>();
        variables.put("serviceRequestStatus", new VariableValueDto().type("String").value("IN_PROGRESS"));
        variables.put("actorId", new VariableValueDto().type("String").value("actor1"));

//        List<ServiceRequestToDo> serviceRequestToDosList = new ArrayList<>();
//        var serviceRequestToDo1 = new ServiceRequestToDo();
//        serviceRequestToDo1.setServiceRequestToDoId("1");
//        serviceRequestToDo1.setServiceRequestToDoStatus("IN PROGRESS");
//        var serviceRequestToDo2 = new ServiceRequestToDo();
//        serviceRequestToDo2.setServiceRequestToDoId("1");
//        serviceRequestToDo2.setServiceRequestToDoStatus("IN PROGRESS");
//        serviceRequestToDosList.add(serviceRequestToDo1);
//        serviceRequestToDosList.add(serviceRequestToDo2);

        variables.put("serviceRequestToDos",
                new VariableValueDto().value(List.of("1", "2")));

//        VariableValueDto variableValueDto = VariableValueDto.fromTypedValue(historicVariableUpdate.getTypedValue());
//        dto.value = variableValueDto.getValue();
//        dto.variableType = variableValueDto.getType();
//        dto.valueInfo = variableValueDto.getValueInfo();

        startProcessInstanceDto.variables(variables);
//        var process =  processDefinitionApi.startProcessInstanceByKey ("nameChangeProcess",
//                startProcessInstanceDto);

        new ProcessDefinitionApi(client).startProcessInstanceByKey("nameChangeProcess",
                startProcessInstanceDto);


    }

    public void documentUpload() {
        // find task

        // complete task
    }

}
