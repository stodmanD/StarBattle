package com.example.hwpart6.command.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.ListAppender;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import com.example.hwpart6.command.Command;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogErrorCommandTest {
    @Mock private Command command;
    @Mock private Exception exception;
    @InjectMocks private LogErrorCommand logErrorCommand;

    private final ListAppender listAppender = new ListAppender<>();
    private final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    @BeforeEach
    public void beforeTest() {
        listAppender.start();
        context.getLogger(LogErrorCommand.class).addAppender(listAppender);
    }

    @AfterEach
    public void afterTest() {
        listAppender.stop();
    }

    @Test
    void shouldLogError() {
        // given
        var exceptionMessage = "exceptionMessage";
        when(exception.getMessage())
                .thenReturn(exceptionMessage);

        // when
        logErrorCommand.execute();

        // verify
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(1, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains(exceptionMessage))
        );
    }
}