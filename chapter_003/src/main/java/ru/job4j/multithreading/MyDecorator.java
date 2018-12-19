package ru.job4j.multithreading;

public class MyDecorator {
    public static void main(String[] args) {
        PrintInterface subject = new QuotesDecorator(new Printer("---"));
        subject.print();
        PrintInterface triple = new TripleDecorator(new QuotesDecorator(new Printer("Something")));
        triple.print();
    }
}

interface PrintInterface {
    void print();
}

abstract class Decorator implements PrintInterface {
    PrintInterface printInterface;

    public Decorator(PrintInterface printInterface) {
        this.printInterface = printInterface;
    }

    @Override
    public void print() {
        printInterface.print();
    }
}

class Printer implements PrintInterface {
    String value;

    public Printer(String value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}

class QuotesDecorator extends Decorator {
    public QuotesDecorator(PrintInterface printInterface) {
        super(printInterface);
    }

    @Override
    public void print() {
        System.out.print("\"");
        super.print();
        System.out.print("\"");
    }
}

class TripleDecorator extends Decorator {
    public TripleDecorator(PrintInterface printInterface) {
        super(printInterface);
    }

    @Override
    public void print() {
        super.print();
        System.out.print(" + ");
        super.print();
        System.out.print(" + ");
        super.print();
    }
}
