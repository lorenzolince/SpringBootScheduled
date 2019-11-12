/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.scheduled.job;

import com.llh.scheduled.service.TestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author lorenzolince
 */
@Component
public class TestJob implements ItestJob {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;
    @Autowired
    private TestServices testServices;

    private String message;

    @Override
    @Scheduled(cron = "${test.cron}")
    public void testJob() {
        testServices.printJob(getMessage());
        // Stop current job
        beanFactory.destroyBean(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
