package com.example.starbattle.Exeptions;

import com.example.starbattle.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TwoRepeatAndLogExceptionCommand implements Command {
    //повторить два раза, потом записать в лог
    private final RepeatExceptionCommand repeatExceptionCommand;

    @Override
    public void execute() {
        try {
            repeatExceptionCommand.execute();
        } catch (Exception e) {
            try {
                repeatExceptionCommand.execute();
            } catch (Exception exception) {
                log.info("Команду не удалось выполнить два раза, было выброшено исключение: {} ", exception.toString());
            }
        }
    }
}
