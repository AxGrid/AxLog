package com.axgrid.log.repository;

import com.axgrid.log.dto.IAxLog;
import java.util.List;

public interface AxLogRepository {

    int getLogCount();
    void add(IAxLog log);
    List<IAxLog> get();
    void done();
    int size();
}
