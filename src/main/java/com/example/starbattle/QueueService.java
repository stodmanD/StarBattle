package com.example.starbattle;

import lombok.RequiredArgsConstructor;

import java.util.Queue;

@RequiredArgsConstructor
public class QueueService implements Command {

    private final Command command;
    private final Queue<Command> commandQueue;


    @Override
    public void execute() {
        commandQueue.add(command);
    }
}
