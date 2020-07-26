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

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;

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
    public void directSend() throws Exception {
        String text= "{\"level\":\"Debug\",\"response\":{\"result\":{\"spin\":{\"win_scatters\":[],\"win_other\":[],\"win_lines\":[],\"win\":0,\"total_win\":0,\"respin\":null,\"reels_add\":null,\"reels\":[4,1,3,2,0,3,5,1,2,4,6,2,5,3,7],\"hotswap\":null,\"freegames\":null,\"extra\":{},\"e\":\"\",\"bonus3\":null,\"bonus2\":null,\"bonus1\":null},\"message\":[],\"free_spin\":null,\"extra\":{\"total_win\":0,\"subgame\":0,\"spin_id\":4515,\"line\":20,\"game\":\"g_bor20\",\"double_step\":0,\"dkf\":1,\"dealer_card\":null,\"bet\":1},\"currency\":\"FUN\",\"credits\":101081430,\"balance\":101081430},\"error\":null,\"code\":200},\"request\":null,\"win\":0,\"reels\":[4,1,3,2,0,3,5,1,2,4,6,2,5,3,7],\"spinId\":4515,\"type\":\"web-response\"}";
        Socket socket = new Socket("127.0.0.1", 5000);
        DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        os.writeBytes(text + "\n");
        os.flush();
        socket.close();
    }

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
