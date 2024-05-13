package com.example.starbattle.Exeptions;

import com.example.starbattle.Command;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Slf4j

public class ExceptionHandlers {

    private static final Map<Type, Map<Type, Command>> handlers = new HashMap<>();

    public static Command handle(Command command, Exception exception) {
        try {
            return handlers.get(command.getClass()).get(exception.getClass());
        } catch (Exception e) {
            log.error("Ошибка {}  во время выполнения действия для command = {} и exception = {}", e.getMessage(), command.getClass(), exception);
        }
        return null;
    }

    public static void setHandlers(Command command, Exception exception, Command exCommand) {
        Map<Type, Command> exCommandMap = new HashMap<>();
        Type exType = exception.getClass();
        Type comType = command.getClass();
        exCommandMap.put(exType, exCommand);
        handlers.put(comType, exCommandMap);
    }
}
