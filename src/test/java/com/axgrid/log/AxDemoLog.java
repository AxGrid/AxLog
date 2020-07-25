package com.axgrid.log;

import com.axgrid.log.dto.AxLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AxDemoLog extends AxLog {

    public String getType() { return "Demo"; }

    long primary = 150;

}
