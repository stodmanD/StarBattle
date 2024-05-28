package com.example.hwpart6.command.queue;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.queue.LinkedListCommandQueue;

import java.util.List;

public class AddElementsListInQueueCommand implements Command {
    private final List<Command> elements;

    public AddElementsListInQueueCommand(List<Command> elements) {
        this.elements = elements;
    }

    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        elements
                .forEach(queue::push);
    }
}
