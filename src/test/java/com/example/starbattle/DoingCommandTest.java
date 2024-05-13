package com.example.starbattle;

import org.junit.jupiter.api.Assertions;
import com.example.starbattle.Exeptions.RepeatExceptionCommand;
import com.example.starbattle.Exeptions.TwoRepeatAndLogExceptionCommand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.LinkedList;
import java.util.Queue;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class DoingCommandTest {
    @Mock
    private Command command;


    @BeforeEach
    void setup() {
        doThrow(new RuntimeException()).when(command).execute();
    }
    @DisplayName("Тест для команды - Реализовать стратегию обработки исключения - повторить два раза, потом записать в лог")
    @Test
    void testTwoRepeatAndLogExceptionCommand() {

        Queue<Command> queue = new LinkedList<>();
        DoingCommand doingCommand = new DoingCommand(queue);

        Command repeat2 = new TwoRepeatAndLogExceptionCommand(new RepeatExceptionCommand(command));
        queue.add(repeat2);

        Assertions.assertEquals(1, queue.size());
        Assertions.assertTrue(queue.contains(repeat2));

        doingCommand.processCommands();

        verify(command, times(2)).execute();


    }
}
