package com.example.starbattle;


import com.example.starbattle.Exeptions.ExceptionHandlers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
public class DoingCommand {

    private final Queue<Command> commandQueue;

    public void processCommands() {
        while (!commandQueue.isEmpty()) {
            Command nextCommand = commandQueue.poll();
            try {
                nextCommand.execute();
            } catch (Exception e) {
                Command handleCommand = ExceptionHandlers.handle(nextCommand, e);
                try {
                    handleCommand.execute();
                } catch (Exception exception) {
                    log.error("При выполнении команды возникла следующая ошибка {} ", e.getMessage());
                }
            }
        }
    }
}