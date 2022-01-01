package main;

public @interface JsonSubtype {
    Class<?> clazz();

    String name();
}

