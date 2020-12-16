package com.example.ChanOfResponsibility1;

public class app {

    public static void main(String[] args) {
        DefaultProcessorSlot defaultProcessorSlot = build2();
        defaultProcessorSlot.execute("call execute from main");
        defaultProcessorSlot.handle("input object");
    }

    // public void handle(){
    //     build2().handle("input object");
    // }

    public static DefaultProcessorSlot build2() {
        DefaultProcessorSlot slot = new DefaultProcessorSlot();
        slot.addLast(new ProcessorSlot1());
        slot.addLast(new ProcessorSlot2());
        slot.addLast(new ProcessorSlot1());
        slot.addLast(new ProcessorSlot1());
        // slot.addLast(new ProcessorSlot3());
        slot.addLast(new ProcessorSlot2());
        slot.addLast(new ProcessorSlot1());
        return slot;
    }
}
