package com.example.hwpart6.queue;



import com.example.hwpart6.command.Command;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListCommandQueue implements Queue<Command> {
    private static LinkedListCommandQueue instance;
    private final LinkedList<Command> elements;

    public LinkedListCommandQueue() {
        this.elements = new LinkedList<>();
    }

    public static synchronized LinkedListCommandQueue getInstance() {
        if (instance == null) {instance = new LinkedListCommandQueue();}
        return instance;
    }

    @Override
    public Iterator<Command> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void push(Command c) {
        elements.push(c);
    }

    @Override
    public Command peek() {
        return elements.getFirst();
    }

    @Override
    public Command pop() {
        Iterator<Command> iter = elements.iterator();
        Command t = iter.next();
        if (t != null) {
            iter.remove();
            return t;
        }
        return null;
    }

    @Override
    public void clear() {
        elements.clear();
    }
}
