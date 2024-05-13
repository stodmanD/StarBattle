package com.example.starbattle.Exeptions;

import com.example.starbattle.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LogExceptionCommand implements Command {
    // записывает информацию о выброшенном исключении в лог
    private final Exception exception;

    @Override
    public void execute() {
        log.info("При выполнении команды, было выброшено исключение: {} ", exception.toString());
    }
}
