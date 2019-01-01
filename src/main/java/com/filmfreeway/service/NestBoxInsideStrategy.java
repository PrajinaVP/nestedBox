package com.filmfreeway.service;

import com.filmfreeway.enums.OrientationEnums;
import com.filmfreeway.model.Box;
import com.filmfreeway.shared.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the strategy to nest boxes inside the outer box.
 *
 * @author Prajina
 */
public class NestBoxInsideStrategy implements NestBoxStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(NestBoxInsideStrategy.class);
    private static final String ORIENTATION_NESTED = OrientationEnums.NESTED.getCode();

    /**
     * This method nests boxes inside the outer box.
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
        LOGGER.debug("************************* START NestedBoxInsideStrategy:: nestBox  *************************************");

        if (childCount < 0) {
            throw new RuntimeException("Cannot nest boxes. Negative child count!");
        }

        if (length < 3 || width < 3) {
            throw new RuntimeException("Cannot nest boxes. Minimum length or width of the box should be 3.");
        }
        //Calculate the dimensions of the outermost/ largest box that will nest the child boxes.
        int outerBoxLength = length + Constants.SPACING * childCount;
        int outerBoxWidth = width + Constants.SPACING * childCount;
        LOGGER.debug("outerBoxLength :: " + outerBoxLength + ", outerBoxWidth :: " + outerBoxWidth);

        List<Box> nestedBoxList = new ArrayList<>();
        Box outerBox = new Box(childCount, ORIENTATION_NESTED, outerBoxLength, outerBoxWidth,
                Constants.CORNER_CHAR, Constants.HORIZONTAL_CHAR, Constants.VERTICAL_CHAR);
        outerBox.setXStart(0);
        outerBox.setYStart(0);
        //Add outer box to list
        nestedBoxList.add(outerBox);

        int startCoord = 0;

        for (int iCount = 0; iCount < childCount; iCount++) {
            outerBoxLength -= Constants.SPACING;
            outerBoxWidth -= Constants.SPACING;
            //Inner box
            Box innerBox = new Box(0, ORIENTATION_NESTED, outerBoxLength, outerBoxWidth);
            startCoord += 2;
            innerBox.setXStart(startCoord);
            innerBox.setYStart(startCoord);
            nestedBoxList.add(innerBox);
        }

        LOGGER.debug("outerBoxLength :: " + outerBoxLength + ", outerBoxWidth :: " + outerBoxWidth);
        LOGGER.debug(nestedBoxList.toString());
        LOGGER.debug("************************* END NestedBoxInsideStrategy:: nestBox  *************************************");

        return nestedBoxList;
    }
}
