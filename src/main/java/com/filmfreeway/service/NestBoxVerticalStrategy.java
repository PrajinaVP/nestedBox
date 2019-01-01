package com.filmfreeway.service;

import com.filmfreeway.enums.OrientationEnums;
import com.filmfreeway.model.Box;
import com.filmfreeway.shared.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NestBoxVerticalStrategy implements NestBoxStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(NestBoxHorizontalStrategy.class);
    private static final String ORIENTATION_VERTICAL = OrientationEnums.VERTICAL.getCode();

    /**
     * This method nests boxes vertically.
     * Each child box has the same dimensions.
     *
     * @param childCount
     * @param length The length (number of horizontal ascii characters) of the box.
     * @param width The width (number of horizontal ascii characters) of the box.
     *
     * @return A list of Box (nested boxes). The outermost box is the first element.
     */

    @Override
    public List<Box> nestBox(int childCount, int length, int width) {

        if (childCount < 0) {
            throw new RuntimeException("Cannot nest boxes. Negative child count!");
        }

        if (length < 3 || width < 3) {
            throw new RuntimeException("Cannot nest boxes. Minimum length or width of the box should be 3.");
        }

        List<Box> nestedBoxList = new ArrayList<>();
        int outerBoxLength = length + Constants.SPACING;
        int outerBoxWidth = Constants.SPACING + (width * childCount) + (childCount - 1);
        LOGGER.debug("outerBoxLength :: " + outerBoxLength + ", outerBoxWidth :: " + outerBoxWidth);

        Box outerBox = new Box(childCount, ORIENTATION_VERTICAL, outerBoxLength, outerBoxWidth, Constants.CORNER_CHAR, Constants.HORIZONTAL_CHAR, Constants.VERTICAL_CHAR);
        outerBox.setXStart(0);
        outerBox.setYStart(0);
        //Add outer box to list
        nestedBoxList.add(outerBox);
        /*
         * The width/ number of vertical ascii chars before the new box.
         * This is used to determine the starting coordinates where the inner boxes should be printed.
         */
        int prevWidth = 0;
        //Inner boxes
        for (int iCount = 0; iCount < childCount; iCount++) {
            Box innerBox = new Box(0, ORIENTATION_VERTICAL, length, width);
            innerBox.setXStart(2);
            innerBox.setYStart(iCount + 2 + prevWidth);
            nestedBoxList.add(innerBox);

            prevWidth += innerBox.getMaxWidth();
        }

        LOGGER.debug(nestedBoxList.toString());
        LOGGER.debug("************************* END NestedBoxVerticalStrategy:: nestBox  *************************************");

        return nestedBoxList;

    }
}
