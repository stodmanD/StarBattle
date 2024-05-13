package com.example.starbattle.Exeptions;

import com.example.starbattle.Command;
import com.example.starbattle.QueueService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueueRepeatExceptionCommand implements Command {
    //ставит в очередь Команду - повторитель команды, выбросившей исключение
    private final RepeatExceptionCommand repeatExceptionCommand;
    private final QueueService queueService;

    @Override
    public void execute() {
        queueService.execute();
    }
}
