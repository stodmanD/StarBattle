package com.example.hwpart6.command.queue;


import com.example.hwpart6.command.log.LogQueueSizeCommand;
import com.example.hwpart6.queue.LinkedListCommandQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddElementInQueueCommandTest {
    @BeforeEach
    public void beforeTest() {
        LinkedListCommandQueue.getInstance().clear();
    }

    @Test
    void shouldAddElementInQueue() {
        // given
        var queue = LinkedListCommandQueue.getInstance();

        // when
        var queueSizeBefore = queue.size();
        new AddElementInQueueCommand(new LogQueueSizeCommand()).execute();

        // then
        var queueSizeAfter = queue.size();
        assertAll(
                () -> assertEquals(0, queueSizeBefore),
                () -> assertEquals(1, queueSizeAfter)
        );
    }
}