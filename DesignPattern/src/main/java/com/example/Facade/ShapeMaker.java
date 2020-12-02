package com.example.Facade;

public class ShapeMaker {

    Circle circle = null;
    Rectangle rectangle = null;

    public ShapeMaker() { //  constructor, no `void` key word
        circle = new Circle();
        rectangle = new Rectangle();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

}
