package com.axgrid.log.service;

import com.axgrid.log.dto.AxLog;
import com.axgrid.log.dto.AxLogLevel;
import com.axgrid.log.dto.IAxLog;
import com.axgrid.log.repository.AxLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.List;

@Slf4j
@Service
public class AxLogService {

    @Autowired
    AxLogRepository repository;

    final ObjectMapper mapper = new ObjectMapper();

    @Value("${ax.log.service.host:localhost}")
    String host;

    @Value("${ax.log.service.post:5000}")
    int port;

    @Scheduled(fixedDelay = 2000)
    public void sendLog() throws IOException {
        List<IAxLog> send = repository.get();

        if (send == null || send.size() == 0) {
            if (log.isTraceEnabled()) log.trace("Not found logs");
            return;
        }

        if (log.isTraceEnabled()) log.trace("Try send {} logs", send.size());
        if (send.size() == 0) return;
        StringWriter sw = new StringWriter();
        for(IAxLog log : send) {
            sw.write(mapper.writeValueAsString(log)+"\n");
        }
        send(sw.toString());
        repository.done();
        if (send.size() == repository.getLogCount()) sendLog();

    }

    private void send(String text) throws IOException {
        if (log.isTraceEnabled()) log.trace("send logs to {}:{} {}", host, port, text);
        try {
            Socket socket = new Socket(host, port);
            DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            os.writeBytes(text + "\n");
            os.flush();
            socket.close();
        }catch (ConnectException e) {
            log.error("Connect to {}:{} exception: {}", host, port, e.getMessage());
        }
    }

    @Async
    public void add(AxLog log) {
        repository.add(log);
        if (log.getLevel() == AxLogLevel.Warn ||
            log.getLevel() == AxLogLevel.Error ||
            log.getLevel() == AxLogLevel.Fatal) {
            try {
                sendLog();
            }catch (IOException ignore) {
            }
        }
    }
}
