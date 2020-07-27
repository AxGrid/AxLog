package com.axgrid.log.dto;

import lombok.Data;

@Data
public abstract class AxLog implements IAxLog {
    AxLogLevel level = AxLogLevel.Debug;
    public String getType() { return this.getClass().getSimpleName(); }
    String[] profiles;
}
