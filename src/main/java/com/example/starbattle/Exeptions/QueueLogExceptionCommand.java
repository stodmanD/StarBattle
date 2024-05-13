package com.example.starbattle.Exeptions;

import com.example.starbattle.Command;
import com.example.starbattle.QueueService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueueLogExceptionCommand implements Command {
    // ставит Команду, пишущую в лог в очередь Команд
    private final LogExceptionCommand logExceptionCommand;
    private final QueueService queueService;

    @Override
    public void execute() {
        queueService.execute();
    }
}
