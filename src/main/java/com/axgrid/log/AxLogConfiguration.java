package com.axgrid.log;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = "com.axgrid.log")
public class AxLogConfiguration {
}
