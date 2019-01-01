package com.filmfreeway.controller;

import com.filmfreeway.enums.OrientationEnums;
import com.filmfreeway.model.Box;
import com.filmfreeway.service.NestedBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/box")
@Api(value="nestBox", description="Nest boxes")
public class NestedBoxController {

	@Autowired
	private NestedBoxService nestedBoxService;

	@ApiOperation(value = "Get a list of nested boxes.",response = Box.class, responseContainer="List")
	@GetMapping("/nest/{childCount}/orientation/{orientation}")
	public List<Box> nestBoxes(@PathVariable int childCount, @PathVariable (value = "orientation") OrientationEnums orientationEnums) {
		return nestedBoxService.nestBoxes(childCount, orientationEnums);
	}

	@ApiOperation(value = "Nest and print ascii characters of boxes.")
	@GetMapping("/nestAndDraw/{childCount}/orientation/{orientation}")
	public char[][] drawNestedBoxes(@PathVariable int childCount,  @PathVariable (value = "orientation") OrientationEnums orientationEnums) {
		return nestedBoxService.drawNestedBoxList(childCount, orientationEnums);
	}

	@GetMapping("/nestAndDraw/{childCount}/orientation/{orientation}/length/{length}/width/{width}")
	public char[][] drawNestedBoxes(@PathVariable int childCount,  @PathVariable (value = "orientation") OrientationEnums orientationEnums, @PathVariable int length, @PathVariable int width) {
		return nestedBoxService.drawNestedBoxList(childCount, orientationEnums, length, width);
	}
}
