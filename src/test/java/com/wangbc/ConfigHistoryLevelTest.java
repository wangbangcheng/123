package com.wangbc;

import com.google.common.collect.Maps;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 15201
 * \* Date: 2019/7/31
 * \* Time: 19:49
 * \* Description:  历史记录测试
 * \
 */
public class ConfigHistoryLevelTest {
    private static final Logger LOGGER= LoggerFactory.getLogger(ConfigHistoryLevelTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_history.cfg.xml");

    @Test
    @Deployment(resources = {"com/wangbc/my-process.bpmn20.xml"})
    public void test() {
        //启动流程
        startActivitiProcess();

        //修改变量
        changVariable();

        //提交表单
        submitTaskData();

        //输出历史变量
        outputHistoricVariable();

        //输出历史活动
        outputHistoricActivity();


        //输出历史表单
        outputHistoricTask();

        //输出历史详情
        outputHistoricDetail();
    }

    private void outputHistoricDetail() {
        List<HistoricDetail> historicDetailForm = activitiRule.getHistoryService()
                .createHistoricDetailQuery()
                .listPage(0, 100);
        for (HistoricDetail historicDetail:historicDetailForm){
            LOGGER.info("historicDetail = {}",toString(historicDetail));
        }
        LOGGER.info("historicDetailForm.size = {}",historicDetailForm.size());
    }

    private void outputHistoricTask() {
        List<HistoricTaskInstance> historicTaskInstances = activitiRule.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .listPage(0, 100);
        for (HistoricTaskInstance historicTaskInstance:historicTaskInstances){
            LOGGER.info("historicTaskInstance = {}",historicTaskInstance);
        }
        LOGGER.info("historicTaskInstances.size = {}",historicTaskInstances.size());
    }

    private void outputHistoricActivity() {
        List<HistoricActivityInstance> historicActivityInstances = activitiRule.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .listPage(0, 100);
        for (HistoricActivityInstance historicActivityInstance:historicActivityInstances){
            LOGGER.info("historicActivityInstance = {}",historicActivityInstance);
        }
        LOGGER.info("historicActivityInstances.size = {}",historicActivityInstances.size());
    }

    private void outputHistoricVariable() {
        List<HistoricVariableInstance> historicVariableInstances = activitiRule.getHistoryService()
                .createHistoricVariableInstanceQuery()
                .listPage(0, 100);
        for (HistoricVariableInstance historicVariableInstance:historicVariableInstances){
            LOGGER.info("historicVariableInstance = {}",historicVariableInstance);
        }
        LOGGER .info("historicVariableInstances.size = {}",historicVariableInstances.size());
    }

    private void submitTaskData() {
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        Map<String, String> properties = Maps.newHashMap();
        properties.put("formKey1","form_Value1");
        properties.put("formKey2","form_Value2");
        properties.put("formKey3","form_Value2");
        activitiRule.getFormService().submitTaskFormData(task.getId(),properties);
//        activitiRule.getTaskService().complete(task.getId());
    }

    private void changVariable() {
        List<Execution> executions = activitiRule.getRuntimeService()
                .createExecutionQuery()
                .listPage(0, 100);
        for (Execution execution:executions){
            LOGGER.info("execution = {}",execution);
        }
        LOGGER.info("execution.size = {}",executions.size());
        String id = executions.iterator().next().getId();
        activitiRule.getRuntimeService().setVariable(id,"keyStart1","keyValue1_Change");
    }

    private void startActivitiProcess() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("keyStart1","keyValue1");
        params.put("keyStart2","keyValue2");
        ProcessInstance processInstance = activitiRule.
                getRuntimeService()
                .startProcessInstanceByKey("my-process",params);
    }

    static String toString(HistoricDetail historicDetail){
        return ToStringBuilder.reflectionToString(historicDetail, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
