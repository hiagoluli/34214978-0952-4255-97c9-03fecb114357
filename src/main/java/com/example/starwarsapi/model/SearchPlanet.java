package com.example.starwarsapi.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchPlanet {
    private int count;
    private Object next;
    private Object previous;
    private ArrayList<Planet> results;
}
