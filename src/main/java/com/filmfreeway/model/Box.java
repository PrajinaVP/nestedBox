package com.filmfreeway.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Box implements Serializable, Comparable<Box>{

    int childCount;
    String orientation;
    List<Box> nestedBoxList;
    int maxLength;
    int maxWidth;
    int xStart;
    int yStart;
    char cornerChar = '+';
    char horizontalChar = '-';
    char verticalChar = '|';

    public Box() {
    }

    public Box(int childCount, String orientation, int maxLength, int maxWidth) {
        this.childCount = childCount;
        this.orientation = orientation;
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
    }

    public Box(int childCount, String orientation, int maxLength, int maxWidth, char cornerChar, char horizontalChar, char verticalChar) {
        this.childCount = childCount;
        this.orientation = orientation;
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        this.cornerChar = cornerChar;
        this.horizontalChar = horizontalChar;
        this.verticalChar = verticalChar;
    }

    public Box(int childCount, String orientation, List<Box> nestedBoxList, int maxLength, int maxWidth, int xStart, int yStart, char cornerChar, char horizontalChar, char verticalChar) {
        this.childCount = childCount;
        this.orientation = orientation;
        this.nestedBoxList = nestedBoxList;
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        this.xStart = xStart;
        this.yStart = yStart;
        this.cornerChar = cornerChar;
        this.horizontalChar = horizontalChar;
        this.verticalChar = verticalChar;
    }

    public char getCornerChar() {
        return cornerChar;
    }

    public void setCornerChar(char cornerChar) {
        this.cornerChar = cornerChar;
    }

    public char getHorizontalChar() {
        return horizontalChar;
    }

    public void setHorizontalChar(char horizontalChar) {
        this.horizontalChar = horizontalChar;
    }

    public char getVerticalChar() {
        return verticalChar;
    }

    public void setVerticalChar(char verticalChar) {
        this.verticalChar = verticalChar;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public List<Box> getNestedBoxList() {
        return nestedBoxList;
    }

    public void setNestedBoxList(List<Box> nestedBoxList) {
        this.nestedBoxList = nestedBoxList;
    }

    public int getXStart() {
        return xStart;
    }

    public void setXStart(int xStart) {
        this.xStart = xStart;
    }

    public int getYStart() {
        return yStart;
    }

    public void setYStart(int yStart) {
        this.yStart = yStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box nestedBox = (Box) o;
        return childCount == nestedBox.childCount &&
                maxLength == nestedBox.maxLength &&
                maxWidth == nestedBox.maxWidth &&
                xStart == nestedBox.xStart &&
                yStart == nestedBox.yStart &&
                cornerChar == nestedBox.cornerChar &&
                horizontalChar == nestedBox.horizontalChar &&
                verticalChar == nestedBox.verticalChar &&
                Objects.equals(orientation, nestedBox.orientation) &&
                Objects.equals(nestedBoxList, nestedBox.nestedBoxList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childCount, orientation, nestedBoxList, maxLength, maxWidth, xStart, yStart, cornerChar, horizontalChar, verticalChar);
    }

    @Override
    public String toString() {
        return "Box{" +
                "childCount=" + childCount +
                ", orientation='" + orientation + '\'' +
                ", nestedBoxList=" + nestedBoxList +
                ", maxLength=" + maxLength +
                ", maxWidth=" + maxWidth +
                ", xStart=" + xStart +
                ", yStart=" + yStart +
                ", cornerChar=" + cornerChar +
                ", horizontalChar=" + horizontalChar +
                ", verticalChar=" + verticalChar +
                '}';
    }

    @Override
    public int compareTo(Box nestedBox) {
        return this.maxLength - nestedBox.maxLength;
    }

    /**
     * Comparator to sort boxes list or array in descending order of width.
     */
    public static Comparator<Box> WidthComparator = new Comparator<Box>() {

        @Override
        public int compare(Box nestedBox1, Box nestedBox2) {
            return (int) (nestedBox2.getMaxWidth() - nestedBox1.getMaxWidth());
        }
    };

    /**
     * Comparator to sort boxes list or array in descending order of length
     */
    public static Comparator<Box> LengthComparator = new Comparator<Box>() {

        @Override
        public int compare(Box nestedBox1, Box nestedBox2) {
            return (int) (nestedBox2.getMaxLength() - nestedBox1.getMaxLength());
        }
    };
}
