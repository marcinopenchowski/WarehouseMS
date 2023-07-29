package com.wms.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryName {
    HEADPHONES("słuchawki"),
    COMPUTER("komputer"),
    NOTEBOOK("laptop"),
    MOUSE("myszka"),
    KEYBOARD("klawiatura");

    private final String name;
}
