package com.example.ChanOfResponsibility1;

public class DefaultProcessorSlot extends AbstractLinkedProcessorSlot {

    private AbstractLinkedProcessorSlot<Object> first = new AbstractLinkedProcessorSlot<Object>() {
        @Override
        protected void execute(Object o) {
            //do noting
            System.out.println("first execute: " + o);
        }
    };
    private AbstractLinkedProcessorSlot<Object> end = first;

    /**
     * add to linked list
     **/
    public void addLast(AbstractLinkedProcessorSlot slot) { // protocolProcessor
        end.setNext(slot);
        end = slot;
    }


    /**
     * handle all slot
     **/
    @Override
    public void handle(Object o) {
        first.handle(o);
    }
    @Override
    protected void execute(Object o) {
        first.execute(o);
    }
}