package com.example.ChanOfResponsibility1;

public class ProcessorSlot1 extends AbstractLinkedProcessorSlot {
    @Override
    protected void execute(Object o) {
        System.out.println("slot1: " + o);
    }
}
