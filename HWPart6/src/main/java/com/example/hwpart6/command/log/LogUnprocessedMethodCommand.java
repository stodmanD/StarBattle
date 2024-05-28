package com.example.hwpart6.command.log;

import com.example.hwpart6.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUnprocessedMethodCommand implements Command {
    private static final Logger log = LoggerFactory.getLogger(LogUnprocessedMethodCommand.class);
    private final String interfaceName;
    private final String methodName;

    public LogUnprocessedMethodCommand(String interfaceName, String methodName) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
    }

    @Override
    public void execute() {
        log.error(
                "Error in interface: {}, verdict: {} is unprocessed method",
                interfaceName,
                methodName);
    }
}
