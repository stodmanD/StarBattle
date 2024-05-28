package com.example.hwpart6.command.queue;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.queue.LinkedListCommandQueue;

public class DeleteFirstElemenInQueueCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.pop();
    }
}
