package com.axgrid.log;

import com.axgrid.log.dto.AxLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AxDemoLog extends AxLog {

    public String getType() { return "Demo"; }
    public Integer[] Array = new Integer[] { 5,10,15,25, 560 };
    Level2Log level2 = new Level2Log();
    long primary = 150;

    public String getGlobalType() { return "global"; }

    @Data
    public static class Level2Log {
        Level3Log level3 = new Level3Log();
    }

    @Data
    public static class Level3Log {
        public Integer[] Level3Array = new Integer[] { 5,10,15,25, 560 };
    }

}
