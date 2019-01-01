package com.filmfreeway.service;

import com.filmfreeway.enums.OrientationEnums;
import com.filmfreeway.model.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.filmfreeway.shared.Constants.SMALLEST_DIMENSION;

/**
 * This class provides all the methods/ services to print ascii art boxes.
 *
 * @author Prajina
 */
@Service
@Component
public class NestedBoxService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NestedBoxService.class);

	/**
	 * This is the 2 dimensional array that stores the ascii characters of the nested boxes.
	 */
	public char[][] nestedBoxAsciiCharPosArray;

	public NestedBoxService() {
	}

	/**
	 * This method creates a 2 dimensional array with the ascii characters of the nested boxes.
	 *
	 * @param box
	 * @return
	 */
	public char[][] getNestedBoxArray(Box box) {
		LOGGER.trace("****** START NestedBoxService :: getNestedBoxArray ******");

		if (box == null) {
			throw new RuntimeException("Unable to generate ascii array. Empty box!");
		}
		LOGGER.debug("Box :: = " + box.toString());

		int length = box.getMaxLength();
		int width = box.getMaxWidth();
		int xStart = box.getXStart();
		int yStart = box.getYStart();
		char cornerChar = box.getCornerChar();
		char horizontalChar = box.getHorizontalChar();
		char verticalChar = box.getVerticalChar();

		// Starting X coordinate where the ascii characters of the box should be printed.
		int xCoord;
		// Starting Y coordinate where the ascii characters of the box should be printed.
		int yCoord = yStart;

		for (int yCount = 0; yCount < width; yCount++) {
			//Reset X coordinate for each column
			xCoord = xStart;
			for (int xCount = 0; xCount < length; xCount++) {
				if ((yCount == 0 && (xCount == 0 || xCount == length - 1))
						|| (yCount == width - 1 && (xCount == 0 || xCount == length - 1))) {
					nestedBoxAsciiCharPosArray[xCoord][yCoord] = cornerChar;
				} else if (yCount == 0 || yCount == width - 1 || xCount == 0 || xCount == length - 1) {
					// iCount > 1 and iCount < height
					if (xCount == 0 || xCount == length - 1) {
						nestedBoxAsciiCharPosArray[xCoord][yCoord] = verticalChar;
					} else {
						nestedBoxAsciiCharPosArray[xCoord][yCoord] = horizontalChar;
					}
				} else {
					nestedBoxAsciiCharPosArray[xCoord][yCoord] = ' ';
				}

				xCoord++;
			}
			yCoord++;
		}
		LOGGER.trace("****** END NestedBoxService :: getNestedBoxArray ******");
		return  nestedBoxAsciiCharPosArray;
	}

	/**
	 * This method nests and prints the smallest ascii char boxes.
	 * The length and width of the smallest box is 3.
	 *
	 * @param childCount The number of child boxes.
	 * @param orientation The direction/ orientation the boxes are to be grouped.
	 *                    Orientation could be horizontal, vertical or nested.
	 *
	 * @return A 2-dimensional array of ascii charactes of all the nested boxes.
	 */
	public char[][] drawNestedBoxList(int childCount, OrientationEnums orientation) {
		LOGGER.trace("****** NestedBoxService :: getNestedBoxArray ******");
		return drawNestedBoxList(childCount, orientation, SMALLEST_DIMENSION, SMALLEST_DIMENSION);
	}

	/**
	 * This method nests and prints ascii char boxes.
	 *
	 * @param childCount The number of child boxes.
	 * @param orientation The direction/ orientation the boxes are to be grouped.
	 *                    Orientation could be horizontal, vertical or nested.
	 * @param length The length (number of horizontal ascii characters) of the box.
	 * @param width The width (number of vertical ascii characters) of the box.
	 *
	 * @return A 2-dimensional array of ascii charactes of all the nested boxes.
	 */
	public char[][] drawNestedBoxList(int childCount, OrientationEnums orientation, int length, int width) {
		LOGGER.trace("****** NestedBoxService :: getNestedBoxArray ******");
		List<Box> nestedBoxList = nestBoxes(childCount, orientation, length, width);
		return drawNestedBoxList(nestedBoxList);
	}

	/**
	 * This method nests boxes per orientation.
	 *
	 * @param childCount The number of child boxes.
	 * @param orientation The direction/ orientation the boxes are to be grouped.
	 *                    Orientation could be horizontal, vertical or nested.
	 *
	 * @return A list of nested boxes.
	 */
	public List<Box> nestBoxes(int childCount, OrientationEnums orientation) {
		LOGGER.trace("****** NestedBoxService :: nestBoxes ******");
		LOGGER.trace("childCount :: " + childCount + ", orientation :: " + orientation);
		return nestBoxes(childCount, orientation, SMALLEST_DIMENSION, SMALLEST_DIMENSION);
	}

	/**
	 * This method nests boxes per orientation.
	 *
	 * @param childCount The number of child boxes.
	 * @param orientation The direction/ orientation the boxes are to be grouped.
	 *                    Orientation could be horizontal, vertical or nested.
	 * @param length The length (number of horizontal ascii characters) of the box.
	 * @param width The width (number of vertical ascii characters) of the box.
	 *
	 * @return A list of boxes that are nested.
	 */
	public List<Box> nestBoxes(int childCount, OrientationEnums orientation, int length, int width) {
		LOGGER.trace("****** START NestedBoxService :: nestBoxes ******");
		LOGGER.trace("childCount :: " + childCount + ", orientation :: " + orientation + ", length :: " + length + ", width :: " + width);
		if (childCount < 0) {
			throw new RuntimeException("Cannot nest boxes. Negative child count!");
		}

		if (orientation == null || orientation.equals("")) {
			throw new RuntimeException("Cannot nest boxes. Empty orientation!");
		}

		if (length < 3 || width < 3) {
			throw new RuntimeException("Cannot nest boxes. Minimum length or width of the box is 3.");
		}

		// Get the strategy to nest boxes.
		NestBoxStrategy nestBoxStrategy = getNestBoxStrategy(orientation);
		List<Box> nestedBoxList = nestBoxStrategy.nestBox(childCount, length, width);

		LOGGER.debug("NESTED BOX LIST::");
		LOGGER.debug(nestedBoxList.toString());
		LOGGER.trace("****** END NestedBoxService :: nestBoxes ******");

		return nestedBoxList;
	}

	/**
	 * This method gets the strategy to nest boxes..
	 *
	 * @param orientation The direction/ orientation the boxes are to be grouped.
	 *                    Orientation could be horizontal, vertical or nested.
	 * @return
	 */
	public NestBoxStrategy getNestBoxStrategy(OrientationEnums orientation) {
		switch (orientation){
			case HORIZONTAL:
				return new NestBoxHorizontalStrategy();
			case VERTICAL:
				return new NestBoxVerticalStrategy();
			case NESTED:
				return new NestBoxInsideStrategy();
			default:
				return new NestBoxInsideStrategy();
		}
	}

	/**
	 * This method prints the nested boxes.
	 *
	 * @param nestedBoxList A list of boxes in descending order of size. The fist element is the outermost box.
	 *
	 * @return A 2-dimensional array of ascii characters representing the nested boxes.
	 */
	public char[][] drawNestedBoxList(List<Box> nestedBoxList) {

		if (nestedBoxList == null || nestedBoxList.isEmpty()) {
			throw new RuntimeException("No boxList available for nesting!");
		}

		/*
		 * The first element in the list is the largerst/ outermost box. Get tne max length and width to initialize the
		 * 2-dimensional array to store the ascii characters after nesting the boxes.
		 */
		Box outerBox = nestedBoxList.get(0);
		int maxOuterLength = outerBox.getMaxLength();
		int maxOuterWidth = outerBox.getMaxWidth();
		//Initialize the array with the dimensions of the outermost/ largest box.
		nestedBoxAsciiCharPosArray = new char[maxOuterLength][maxOuterWidth];

		//Nest and populate the ascii characters for boxes in the list.
		for (Box nestedBox : nestedBoxList) {
			getNestedBoxArray(nestedBox);
		}
		//Print the nested boxes.
		for(int yCount = 0; yCount < maxOuterWidth; yCount++) {
			for (int xCount = 0; xCount < maxOuterLength; xCount++) {
				System.out.print(nestedBoxAsciiCharPosArray[xCount][yCount]);
			}
			System.out.println("");
		}

		System.out.println("_________________________________________________________________");
		return nestedBoxAsciiCharPosArray;
	}
}