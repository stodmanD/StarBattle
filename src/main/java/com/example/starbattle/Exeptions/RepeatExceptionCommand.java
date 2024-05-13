package com.example.starbattle.Exeptions;

import com.example.starbattle.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RepeatExceptionCommand implements Command {
    //повторяет Команду, выбросившую исключение
    private final Command command;

    @Override
    public void execute() {
        command.execute();
    }
}
