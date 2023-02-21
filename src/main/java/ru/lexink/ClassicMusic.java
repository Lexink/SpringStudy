package ru.lexink;

import org.springframework.stereotype.Component;


public class ClassicMusic implements Music {
    @Override
    public String getSong() {
        return "Moon Sonata";
    }
}
