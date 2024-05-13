package com.example.starbattle.Exeptions;

import com.example.starbattle.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepeatAndLogExceptionCommand implements Command {
    //при первом выбросе исключения повторить команду, при повторном выбросе исключения записать информацию в лог
    private final LogExceptionCommand logExceptionCommand;
    private final RepeatExceptionCommand repeatExceptionCommand;

    @Override
    public void execute() {
        try {
            repeatExceptionCommand.execute();
        } catch (Exception e) {
            logExceptionCommand.execute();
        }
    }
}
