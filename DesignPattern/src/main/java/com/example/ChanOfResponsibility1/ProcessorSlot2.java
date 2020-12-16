package com.example.ChanOfResponsibility1;

public class ProcessorSlot2 extends AbstractLinkedProcessorSlot {
    @Override
    protected void execute(Object o) {
        System.out.println("slot2: " + o);
    }
}
