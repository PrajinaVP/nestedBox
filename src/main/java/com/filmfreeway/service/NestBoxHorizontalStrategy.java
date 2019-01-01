package com.filmfreeway.service;

import com.filmfreeway.enums.OrientationEnums;
import com.filmfreeway.model.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import com.filmfreeway.shared.Constants;
/**
 * This is the strategy to nest boxes horizontally.
 *
 * @author Prajina
 */
public class NestBoxHorizontalStrategy implements NestBoxStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(NestBoxHorizontalStrategy.class);
    private static final String ORIENTATION_HORIZONTAL = OrientationEnums.HORIZONTAL.getCode();

    /**
     * This method nests boxes horizontally.
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
        //Calculate the dimensions of the outermost/ largest box that will nest the child boxes.
        int outerBoxLength = Constants.SPACING + (length * childCount) + (childCount - 1);
        int outerBoxWidth = width + Constants.SPACING;
        LOGGER.debug("outerBoxLength :: " + outerBoxLength + ", outerBoxWidth :: " + outerBoxWidth);

        Box outerBox = new Box(childCount, ORIENTATION_HORIZONTAL, outerBoxLength, outerBoxWidth,
                Constants.CORNER_CHAR, Constants.HORIZONTAL_CHAR, Constants.VERTICAL_CHAR);
        outerBox.setXStart(0);
        outerBox.setYStart(0);
        //Add outer box to list
        nestedBoxList.add(outerBox);

        /*
         * The length/ number of horizontal ascii chars before the new box.
         * This is used to determine the starting coordinates where the inner boxes should be printed.
         */
        int prevLength = 0;
        //Inner boxes
        for (int iCount = 0; iCount < childCount; iCount++) {
            Box innerBox = new Box(0, ORIENTATION_HORIZONTAL, length, width);
            innerBox.setXStart(iCount + 2 + prevLength);
            innerBox.setYStart(2);
            nestedBoxList.add(innerBox);

            prevLength += innerBox.getMaxLength();
        }
        LOGGER.debug(nestedBoxList.toString());
        LOGGER.debug("************************* END NestedBoxHorizontalStrategy:: nestBox  *************************************");

        return nestedBoxList;
    }

}
