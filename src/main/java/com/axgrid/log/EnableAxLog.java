package com.axgrid.log;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AxLogConfiguration.class})
public @interface EnableAxLog {
}
