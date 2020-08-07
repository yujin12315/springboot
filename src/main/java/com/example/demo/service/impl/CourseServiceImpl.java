package com.example.demo.service.impl;

import com.example.demo.common.Exception.CustomException;
import com.example.demo.common.Vo.ResponseJson;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.service.CourseService;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    @Transactional
    public ResponseJson getList() throws InterruptedException {

        // 业务数据
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("insurance_start_date", "20100101");
        condition.put("insurance_end_date", "20201231");
        condition.put("death_day", "20200405");
        condition.put("insurance_amount", "10000000");
        condition.put("amount_of_compensation", "20000000");

        getResult(getDRL("DRL001"), condition);

        ResponseJson resultJson = new ResponseJson();
        resultJson.setStatus("200");
        resultJson.setCode("3000");
        resultJson.setMessage("DataBase OK");

        return resultJson;
    }

    // 规则引擎Check完了后的数据返回
    public Map<String, String> getResult(String drl, Map<String, Object> condition) {

        // ready
        Map<String, String> resultMap = new HashMap<String, String>();

        for (Map.Entry<String, Object> conditionMap : condition.entrySet()) {
            resultMap.put(conditionMap.getKey(), "NG");
        }

        condition.put("resultMap", resultMap);

        try {
            KnowledgeBase kbase = readKnowledgeBase(drl);
            StatefulKnowledgeSession ksession = kbase
                    .newStatefulKnowledgeSession();
            // 业务数据插入
            ksession.insert(condition);
            // 执行规则
            ksession.fireAllRules();

            // 释放内存资源
            ksession.dispose();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(resultMap);
        return resultMap;
    }

    // 收集应用规则 从DataBase中取得规则数据
    private KnowledgeBase readKnowledgeBase(String drl) throws Exception {

        Resource resource = ResourceFactory
                .newReaderResource((Reader) new StringReader(drl));

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
                .newKnowledgeBuilder();
        kbuilder.add(resource, ResourceType.DRL);

        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        return kbase;
    }

    public String getDRL(String drlId) throws CustomException {
        // 連接數據庫
        List<String> ruleList = courseMapper.getInfo(drlId);

        StringBuffer drlsb = new StringBuffer();
        System.out.println("DataBase:" + ruleList.size());


        return drlsb.toString();
    }

}
