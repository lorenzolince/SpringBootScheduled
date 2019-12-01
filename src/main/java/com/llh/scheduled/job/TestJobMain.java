/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.scheduled.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 *
 * @author lorenzolince
 */
@Component
public class TestJobMain implements ItestJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestJobMain.class);

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;
    @Autowired
    AutowireCapableBeanFactory beanFactory;
    @Autowired
    TestJob testJob;

    @Override
    @Scheduled(initialDelayString = "${test.initial.delay.in.milliseconds:10000}", fixedRateString = "${test.fixed.delay.in.milliseconds:1000}")
    public void testJob() {
        String testValue = System.getProperty("test.job");
        LOGGER.info("test job main #################  \n" + testValue);
        if (testValue.equals("START")) {
            System.setProperty("test.cron", "25 * * * * ?");
            testJob.setInstanceBean("testJob");
            postProcessor.postProcessAfterInitialization(testJob, "testJob");

            System.setProperty("test.cron", "45 * * * * ?");
            TestJob testJob2 = (TestJob) beanFactory.createBean(TestJob.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
            testJob2.setInstanceBean("testJob2");
            postProcessor.postProcessAfterInitialization(testJob2, "testJob2");
            System.setProperty("test.job", "DEFAULT");

        }

    }
}
