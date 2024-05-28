package com.example.hwpart6.command.log;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.queue.LinkedListCommandQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogQueueSizeCommand implements Command {
    private static final Logger log = LoggerFactory.getLogger(LogQueueSizeCommand.class);

    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        log.info("Queue size: {}", queue.size());
    }
}
