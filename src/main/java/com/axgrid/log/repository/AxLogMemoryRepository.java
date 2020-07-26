package com.axgrid.log.repository;

import com.axgrid.log.dto.AxLog;
import com.axgrid.log.dto.IAxLog;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Repository
public class AxLogMemoryRepository implements AxLogRepository {

    @Getter
    final int logCount = 100;
    List<IAxLog> lastPack = null;

    final Queue<IAxLog> logs = new LinkedBlockingQueue<>();

    public void add(IAxLog logz) {
        logs.add(logz);
    }

    public int size() {return logs.size();}


    public List<IAxLog> get() {
        if (lastPack != null) return lastPack;
        lastPack = getForSend();
        if (lastPack.size() == 0) lastPack = null;
        return lastPack;
    }

    public void done() {
        lastPack = null;
    }

    private List<IAxLog> getForSend() {
        List<IAxLog> res = new ArrayList<>();
        while (res.size() < logCount && logs.size() > 0) {
            res.add(logs.poll());
        }
        return res;
    }

}
