package com.example.practicer2dbc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class Person {
    private final String id;
    private final String name;
    private final int age;

}
