package com.example.ChanOfResponsibility1;

public abstract class AbstractLinkedProcessorSlot<T> {
    private AbstractLinkedProcessorSlot<T> next = null;

    /**
     * each call all business handle function
     **/
    public void handle(T t) {
        while (next != null) {
            next.execute(t);
            next = next.next;
        }
    }

    /**
     * append to linked list
     **/
    protected void setNext(AbstractLinkedProcessorSlot slot) {
        this.next = slot;
    }

    /**
     * child class impl execute
     **/
    protected abstract void execute(T t);
}