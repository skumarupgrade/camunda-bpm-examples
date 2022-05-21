package org.camunda.bpm;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.variable.ClientValues;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderProcessingHandler {

    @Bean
    @ExternalTaskSubscription("invoiceCreator")
    public ExternalTaskHandler invoiceCreatorHandler() {
        return (externalTask, externalTaskService) -> {
            // instantiate an invoice object
            Invoice invoice = new Invoice("A123");

            // create an object typed variable with the serialization format XML
            ObjectValue invoiceValue = ClientValues
                    .objectValue(invoice)
                    .serializationDataFormat("application/xml")
                    .create();

            // add the invoice object and its id to a map
            Map<String, Object> variables = new HashMap<>();
            variables.put("invoiceId", invoice.id);
            variables.put("invoice", invoiceValue);

            // select the scope of the variables
            boolean isRandomSample = Math.random() <= 0.5;
            if (isRandomSample) {
                externalTaskService.complete(externalTask, variables);
            } else {
                externalTaskService.complete(externalTask, null, variables);
            }

            System.out.println("The External Task " + externalTask.getId() +
                    " has been completed!");
//            // add your business logic here
//            externalTaskService.complete(externalTask);
        };
    }

    @Bean
    @ExternalTaskSubscription("invoiceArchiver")
    public ExternalTaskHandler invoiceArchiverHandler() {
        return (externalTask, externalTaskService) -> {
            TypedValue typedInvoice = externalTask.getVariableTyped("invoice");
            Invoice invoice = (Invoice) typedInvoice.getValue();
//          throw new RuntimeException("run time error");
            System.out.println("Invoice on process scope archived: " + invoice);
            externalTaskService.complete(externalTask);
//            // add your business logic here
//            externalTaskService.complete(externalTask);
        };
    }

}
