package com.animals;

interface Animal {
    void sound();
}

class Dog implements Animal {
    @Override
    public void sound() { System.out.println("Bark"); }
}

class Cat implements Animal {
    @Override
    public void sound() { System.out.println("Meow"); }
}

class Lion implements Animal {
    @Override
    public void sound() { System.out.println("Roar"); }
}
