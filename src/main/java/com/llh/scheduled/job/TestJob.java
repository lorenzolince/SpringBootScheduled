/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.scheduled.job;

import com.llh.scheduled.service.TestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 *
 * @author lorenzolince
 */
@Component
public class TestJob implements ItestJob {

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;
    @Autowired
    private TestServices testServices;

    private String instanceBean;

    @Override
    @Scheduled(cron = "${test.cron}")
    public void testJob() {
        testServices.printJob(getInstanceBean());
        postProcessor.postProcessBeforeDestruction(this, getInstanceBean());
    }

    public String getInstanceBean() {
        return instanceBean;
    }

    public void setInstanceBean(String instanceBean) {
        this.instanceBean = instanceBean;
    }

}
