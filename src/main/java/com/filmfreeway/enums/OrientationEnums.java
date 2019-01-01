package com.filmfreeway.enums;

/**
 * The orientation for nesting boxes.
 *
 * @author Prajina
 */
public enum OrientationEnums {
    HORIZONTAL ("HORIZONTAL"),
    VERTICAL ("VERTICAL"),
    NESTED ("NESTED");

    private final String code;

    OrientationEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static boolean isHorizontal(String code) {
        return code.equals(HORIZONTAL.getCode());
    }

    public static boolean isVertical(String code) {
        return code.equals(VERTICAL.getCode());
    }

    public static boolean isNested(String code) {
        return code.equals(NESTED.getCode());
    }
}
