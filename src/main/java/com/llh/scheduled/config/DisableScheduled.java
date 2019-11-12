/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.scheduled.config;

import com.llh.scheduled.job.ItestJob;
import com.llh.scheduled.job.TestJobMain;
import com.llh.scheduled.job.TestJob;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 *
 * @author lorenzolince
 */
@Component
public class DisableScheduled implements CommandLineRunner {

   
    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;
    @Autowired
    List<ItestJob> testJob;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("######### DisableScheduled ###########");
        for(ItestJob test: testJob){
         postProcessor.postProcessBeforeDestruction(test, "testJob");
        }
       
    }

}
