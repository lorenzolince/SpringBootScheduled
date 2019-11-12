/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.scheduled.service;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author lorenzolince
 */
@Service
public class TestServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServices.class);

    public void printJob(String message) {
        LOGGER.info("$$$$$$$$$$$$$$$$$$$$$$$  $$$$$$$$$$$$$$$$$$$$$$$ ");
        LOGGER.info(message + " " + new Date());

    }

}
