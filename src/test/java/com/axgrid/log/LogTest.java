package com.axgrid.log;

import com.axgrid.log.service.AxLogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@Import(TestApplication.class)
@ActiveProfiles("test")
@TestPropertySource(properties = "debug=true")
@Slf4j
public class LogTest {

    @Autowired
    AxLogService logService;

    @Test
    public void testLogService() throws Exception {
        AxDemoLog dl = new AxDemoLog();
        logService.add(dl);
        logService.add(dl);
        logService.add(dl);
        logService.sendLog();
        Thread.sleep(1000);
        logService.sendLog();
    }

}
