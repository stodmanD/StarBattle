package com.example.hwpart6.service;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.ListAppender;

import com.example.hwpart6.command.log.LogQueueSizeCommand;
import com.example.hwpart6.command.queue.AddElementInQueueCommand;
import com.example.hwpart6.command.log.LogErrorCommand;
import com.example.hwpart6.queue.LinkedListCommandQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class CommandQueueOperatingServiceTest {
    @Spy private LogQueueSizeCommand logQueueSizeCommand;
    private final CommandQueueOperatingService queueOperatingService = new CommandQueueOperatingService();
    private final ListAppender listAppender = new ListAppender<>();
    private final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    @BeforeEach
    public void beforeTest() {
        LinkedListCommandQueue.getInstance().clear();
        listAppender.start();
    }

    @AfterEach
    public void afterTest() {
        listAppender.stop();
    }

    @Test
    void shouldLogErrorWhenCommandException() {
        // given
        context.getLogger(LogErrorCommand.class).addAppender(listAppender);
        new AddElementInQueueCommand(logQueueSizeCommand).execute();
        doThrow(new RuntimeException("shouldLogErrorWhenCommandException"))
                .when(logQueueSizeCommand)
                .execute();

        // when
        queueOperatingService.process();

        // then
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(1, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains("verdict: shouldLogErrorWhenCommandException"))
        );
    }

    @Test
    void shouldEnrichQueueWhenCommandException() {
        // given
        context.getLogger(LogQueueSizeCommand.class).addAppender(listAppender);
        new AddElementInQueueCommand(logQueueSizeCommand).execute();
        doThrow(new IllegalStateException("shouldEnrichQueueWhenCommandException"))
                .doCallRealMethod()
                .when(logQueueSizeCommand)
                .execute();

        // when
        queueOperatingService.process();

        // then
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(1, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains("Queue size: 1"))
        );
    }
}