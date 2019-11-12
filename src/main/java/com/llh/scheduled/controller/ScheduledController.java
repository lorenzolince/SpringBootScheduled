/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.scheduled.controller;

import com.llh.scheduled.job.TestJobMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lorenzolince
 */
@RestController
@RequestMapping(name = "Scheduled", value = "/api/scheduled")
public class ScheduledController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledController.class);
    @Autowired
    private TestJobMain testJobMain;

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @RequestMapping(path = "/start", method = RequestMethod.GET)
    public void startob() {
        LOGGER.info("########### start job ##############");
        System.setProperty("test.job", "START");

    }

    @RequestMapping(path = "/disable/job", method = RequestMethod.GET)
    public void disableJob() {
        LOGGER.info("########### disable jobs##############");
        postProcessor.postProcessBeforeDestruction(testJobMain, "testJobMain");

    }

    @RequestMapping(path = "/enable/jpob", method = RequestMethod.GET)
    public void enableJob(@RequestParam(name = "initial", defaultValue = "0") String initial,
            @RequestParam(name = "rate", defaultValue = "5000") String rate) {
        LOGGER.info("########### enable job ##############");
        System.setProperty("test.initial.delay.in.milliseconds", initial);
        System.setProperty("test.fixed.delay.in.milliseconds", rate);
        System.setProperty("test.job", "DEFAULT");
        postProcessor.postProcessAfterInitialization(testJobMain, "testJobMain");

    }
}
