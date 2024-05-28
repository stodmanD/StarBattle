package com.example.hwpart6.command.exception;


import com.example.hwpart6.command.log.LogErrorCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LogErrorExceptionCommandTest {
    @InjectMocks private LogErrorExceptionCommand logErrorExceptionCommand;

    @Test
    void shouldLogErrorCommandWhenHandleException() {
        try (MockedConstruction<LogErrorCommand> logErrorCommandConstructionMock = mockConstruction(LogErrorCommand.class)) {
            // when
            logErrorExceptionCommand.execute();

            // then
            List<LogErrorCommand> constructed = logErrorCommandConstructionMock.constructed();
            assertEquals(1, constructed.size());
            var logErrorCommandMock = constructed.get(0);
            verify(logErrorCommandMock)
                    .execute();
        }
    }
}