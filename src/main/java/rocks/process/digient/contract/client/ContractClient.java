/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.digient.contract.client;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rocks.process.digient.contract.message.MessageSender;
import rocks.process.digient.domain.Application;
import rocks.process.digient.domain.Customer;
import rocks.process.digient.domain.Mailing;
import rocks.process.digient.domain.Policy;
import rocks.process.digient.message.Message;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ContractClient {

    @Autowired
    ExternalTaskClient client;

    @Value("${camunda-rest.tenantid}")
    private String camundaTenantId;

    @Autowired
    private MessageSender messageSender;

    @PostConstruct
    private void subscribeTopics() {

        client.subscribe("OpenApplicationCase")
                .tenantIdIn(camundaTenantId)
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
                    try {
                        Customer customer = new Customer(externalTask.getVariable("cId"), externalTask.getVariable("cName"), externalTask.getVariable("email"));
                        Application application = new Application(externalTask.getVariable("aId"), externalTask.getVariable("cId"), new Date().toString(), externalTask.getVariable("productId"), externalTask.getVariable("carId"), externalTask.getVariable("age"), externalTask.getVariable("kw"), externalTask.getVariable("licenseRevocation"), externalTask.getVariable("carPrice"), "open", "unknown", "unknown", externalTask.getVariable("retention"));

                        messageSender.send(new Message<>("customer", customer, externalTask.getBusinessKey()));
                        messageSender.send(new Message<>("application", application, externalTask.getBusinessKey()));

                        Map<String, Object> variables = new HashMap<>();
                        variables.put("aDate", application.getADate());

                        externalTaskService.complete(externalTask, variables);
                    } catch (Exception e) {
                        externalTaskService.handleBpmnError(externalTask, "failed");
                    }
                })
                .open();

        client.subscribe("SendRejection")
                .tenantIdIn(camundaTenantId)
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
                    try {
                        Mailing mailing = new Mailing(UUID.randomUUID().toString(), externalTask.getVariable("cId"), externalTask.getVariable("cName"), externalTask.getVariable("email"), "rejection", "file");

                        messageSender.send(new Message<>("mailing", mailing, externalTask.getBusinessKey()));

                        Map<String, Object> variables = new HashMap<>();
                        variables.put("mId", mailing.getMId());

                        externalTaskService.complete(externalTask, variables);
                    } catch (Exception e) {
                        externalTaskService.handleBpmnError(externalTask, "failed");
                    }
                })
                .open();

        client.subscribe("CaptureRejectionClose")
                .tenantIdIn(camundaTenantId)
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
                    try {
                        Application application = new Application(externalTask.getVariable("aId"), externalTask.getVariable("cId"), externalTask.getVariable("aDate"), externalTask.getVariable("productId"), externalTask.getVariable("carId"), externalTask.getVariable("age"), externalTask.getVariable("kw"), externalTask.getVariable("licenseRevocation"), externalTask.getVariable("carPrice"), "close", "reject", externalTask.getVariable("risk"), externalTask.getVariable("retention"));

                        messageSender.send(new Message<>("application", application, externalTask.getBusinessKey()));

                        externalTaskService.complete(externalTask);
                    } catch (Exception e) {
                        externalTaskService.handleBpmnError(externalTask, "failed");
                    }
                })
                .open();

        client.subscribe("IssueClose")
                .tenantIdIn(camundaTenantId)
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
                    try {
                        Application application = new Application(externalTask.getVariable("aId"), externalTask.getVariable("cId"), externalTask.getVariable("aDate"), externalTask.getVariable("productId"), externalTask.getVariable("carId"), externalTask.getVariable("age"), externalTask.getVariable("kw"), externalTask.getVariable("licenseRevocation"), externalTask.getVariable("carPrice"), "close", "accept", externalTask.getVariable("risk"), externalTask.getVariable("retention"));
                        Policy policy = new Policy(UUID.randomUUID().toString(), externalTask.getVariable("cId"), externalTask.getVariable("aId"), new Date().toString(), externalTask.getVariable("productId"), externalTask.getVariable("carId"), "issued", externalTask.getVariable("risk"), externalTask.getVariable("retention"));

                        messageSender.send(new Message<>("application", application, externalTask.getBusinessKey()));
                        messageSender.send(new Message<>("policy", policy, externalTask.getBusinessKey()));

                        Map<String, Object> variables = new HashMap<>();
                        variables.put("pId", policy.getPId());
                        variables.put("pDate", policy.getPDate());

                        externalTaskService.complete(externalTask, variables);
                    } catch (Exception e) {
                        externalTaskService.handleBpmnError(externalTask, "failed");
                    }
                })
                .open();

        client.subscribe("ActivatePolicy")
                .tenantIdIn(camundaTenantId)
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
                    try {
                        Policy policy;
                        Mailing mailing;
                        if(new Random().nextBoolean()){
                            policy = new Policy(externalTask.getVariable("pId"), externalTask.getVariable("cId"), externalTask.getVariable("aId"), new Date().toString(), externalTask.getVariable("productId"), externalTask.getVariable("carId"), "active", externalTask.getVariable("risk"), externalTask.getVariable("retention"));
                            messageSender.send(new Message<>("policy", policy, externalTask.getBusinessKey()));
                            mailing = new Mailing(UUID.randomUUID().toString(), externalTask.getVariable("cId"), externalTask.getVariable("cName"), externalTask.getVariable("email"), "Confirmation", "Policy");
                            messageSender.send(new Message<>("mailing", mailing, externalTask.getBusinessKey()));
                            Map<String, Object> variables = new HashMap<>();
                            variables.put("mId", mailing.getMId());
                            externalTaskService.complete(externalTask, variables);
                        }else {
                            policy = new Policy(externalTask.getVariable("pId"), externalTask.getVariable("cId"), externalTask.getVariable("aId"), new Date().toString(), externalTask.getVariable("productId"), externalTask.getVariable("carId"), "deactivated", externalTask.getVariable("risk"), externalTask.getVariable("retention"));
                            messageSender.send(new Message<>("policy", policy, externalTask.getBusinessKey()));
                            mailing = new Mailing(UUID.randomUUID().toString(), externalTask.getVariable("cId"), externalTask.getVariable("cName"), externalTask.getVariable("email"), "Deactivated policy", "Letter");
                            messageSender.send(new Message<>("mailing", mailing, externalTask.getBusinessKey()));
                            externalTaskService.handleBpmnError(externalTask, "PolicyDeactivated");
                        }
                    } catch (Exception e) {
                        externalTaskService.handleBpmnError(externalTask, "failed");
                    }
                })
                .open();
    }

}
