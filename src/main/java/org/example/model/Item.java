package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
    private final String name;
    private final double price;

    @Override
    public String toString() {
        return name + ": " + price + " EUR";
    }
}
