i
mport org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;

import static org.camunda.bpm.engine.test.assertions.cmmn.ProcessTaskAssert.assertThat;


@Deployment(resources = "deliver-process.bpmn")
public class PhysicalDocumentImagingProcessTest {//extends AbstractActorServicingServerIT {
    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    @Test
    @Deployment
    public void ruleUsageExample() {
        RuntimeService runtimeService = processEngineRule.getRuntimeService();
        var process =
        runtimeService.startProcessInstanceByKey("ruleUsage");

        assertThat(process).isActive();
;
        TaskService taskService = processEngineRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        assertThat(task.getName()).isEqualTo("My Task");

        taskService.complete(task.getId());
        assertThat(runtimeService.createProcessInstanceQuery().count()).isEqualTo(0);
    }
}
