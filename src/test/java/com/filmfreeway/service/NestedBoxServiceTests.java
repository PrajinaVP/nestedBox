package com.filmfreeway.service;

import com.filmfreeway.enums.OrientationEnums;
import com.filmfreeway.model.Box;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.filmfreeway.shared.Constants.SMALLEST_DIMENSION;
import static com.filmfreeway.shared.Constants.SPACING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
public class NestedBoxServiceTests {

    NestedBoxService NestedBoxService;

    public NestedBoxServiceTests() {
    }

    private NestedBoxService getNestedBoxService() {
        if (NestedBoxService == null) {
            NestedBoxService = new NestedBoxService();
        }
        return NestedBoxService;
    }

    @Test
    public void testDrawNestedBoxChildCount() {
        int childCount = 2;
        int length = SMALLEST_DIMENSION;
        int width = SMALLEST_DIMENSION;

        //Horizontal
        char[][] nestedBoxArray = getNestedBoxService().drawNestedBoxList(2, OrientationEnums.HORIZONTAL);
        int outerBoxLength = SPACING + (length * childCount) + (childCount - 1);
        int outerBoxWidth = width + SPACING;
        assertEquals(nestedBoxArray.length, outerBoxLength);
        assertEquals(nestedBoxArray[0].length, outerBoxWidth);

        //Vertical
        nestedBoxArray = getNestedBoxService().drawNestedBoxList(2, OrientationEnums.VERTICAL);
        outerBoxLength = length + SPACING;
        outerBoxWidth = SPACING + (width * childCount) + (childCount - 1);
        assertEquals(nestedBoxArray.length, outerBoxLength);
        assertEquals(nestedBoxArray[0].length, outerBoxWidth);

        //Vertical
        nestedBoxArray = getNestedBoxService().drawNestedBoxList(2, OrientationEnums.NESTED);
        outerBoxLength = length + SPACING * childCount;
        outerBoxWidth = width + SPACING * childCount;
        assertEquals(nestedBoxArray.length, outerBoxLength);
        assertEquals(nestedBoxArray[0].length, outerBoxWidth);

        //@TODO Add negative test cases
    }

    @Test
    public void testDrawNestedBoxList() {
        List<Box> nestedBoxList = null;
        int childCount = 20;
        int length = SMALLEST_DIMENSION;
        int width = SMALLEST_DIMENSION;

        //Horizontal
        nestedBoxList = getNestedBoxService().nestBoxes(childCount, OrientationEnums.HORIZONTAL);
        char[][] nestedBoxArray = getNestedBoxService().drawNestedBoxList(nestedBoxList);
        int outerBoxLength = SPACING + (length * childCount) + (childCount - 1);
        int outerBoxWidth = width + SPACING;
        assertEquals(nestedBoxArray.length, outerBoxLength);
        assertEquals(nestedBoxArray[0].length, outerBoxWidth);

        //Vertical
        nestedBoxList = getNestedBoxService().nestBoxes(childCount, OrientationEnums.VERTICAL);
        nestedBoxArray = getNestedBoxService().drawNestedBoxList(nestedBoxList);
        outerBoxLength = length + SPACING;
        outerBoxWidth = SPACING + (width * childCount) + (childCount - 1);
        assertEquals(nestedBoxArray.length, outerBoxLength);
        assertEquals(nestedBoxArray[0].length, outerBoxWidth);

        //Nested
        nestedBoxList = getNestedBoxService().nestBoxes(childCount, OrientationEnums.NESTED);
        nestedBoxArray = getNestedBoxService().drawNestedBoxList(nestedBoxList);
        outerBoxLength = length + SPACING * childCount;
        outerBoxWidth = width + SPACING * childCount;
        assertEquals(nestedBoxArray.length, outerBoxLength);
        assertEquals(nestedBoxArray[0].length, outerBoxWidth);

        //@TODO Add negative test cases
    }

    @Test
    public void testGetStrategy() {
        //Horizontal
        NestBoxStrategy nestedBoxStrategy = getNestedBoxService().getNestBoxStrategy(OrientationEnums.HORIZONTAL);
        assertEquals(nestedBoxStrategy.getClass(), NestBoxHorizontalStrategy.class);
        assertFalse("Expected to fail", nestedBoxStrategy.getClass().equals(NestBoxVerticalStrategy.class));
        assertFalse("Expected to fail", nestedBoxStrategy.getClass().equals(NestBoxInsideStrategy.class));

        //Vertical
        nestedBoxStrategy = getNestedBoxService().getNestBoxStrategy(OrientationEnums.VERTICAL);
        assertEquals(nestedBoxStrategy.getClass(), NestBoxVerticalStrategy.class);
        assertFalse("Expected to fail", nestedBoxStrategy.getClass().equals(NestBoxHorizontalStrategy.class));
        assertFalse("Expected to fail", nestedBoxStrategy.getClass().equals(NestBoxInsideStrategy.class));

        //Nested
        nestedBoxStrategy = getNestedBoxService().getNestBoxStrategy(OrientationEnums.NESTED);
        assertEquals(nestedBoxStrategy.getClass(), NestBoxInsideStrategy.class);
        assertFalse("Expected to fail", nestedBoxStrategy.getClass().equals(NestBoxHorizontalStrategy.class));
        assertFalse("Expected to fail", nestedBoxStrategy.getClass().equals(NestBoxVerticalStrategy.class));
    }
}