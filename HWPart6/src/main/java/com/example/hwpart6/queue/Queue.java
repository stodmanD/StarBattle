package com.example.hwpart6.queue;

import java.util.Iterator;

public interface Queue<T> {
    Iterator<T> iterator();
    int size();
    void push(T t);
    T peek();
    T pop();

    void clear();
}

