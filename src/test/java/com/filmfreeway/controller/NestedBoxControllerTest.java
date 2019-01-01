package com.filmfreeway.controller;

import com.filmfreeway.enums.OrientationEnums;
import com.filmfreeway.model.Box;
import com.filmfreeway.service.NestedBoxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NestedBoxController.class, secure = false)
public class NestedBoxControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NestedBoxService nestedBoxService;

	Box horizontalBox = new Box(2, OrientationEnums.HORIZONTAL.getCode(), 5, 3, '+', '-', '|'  );
	Box verticalBox = new Box(2, OrientationEnums.VERTICAL.getCode(), 5, 3, '+', '-', '|'  );
	Box nestedBox = new Box(2, OrientationEnums.NESTED.getCode(), 5, 3, '+', '-', '|'  );

	String exampleNestBoxJson = "{\"nestBoxes\":3,\"orientation\":\"HORIZONTAL\"}";
	String exampleNestAndDrawBoxes = "{\"nestAndDrawBoxes\"3,\" orientation\":\"NESTED\", \"length\":5,\"width\":3}";

	@Test
	public void nestBoxes() {
		//@TODO To be implemented
	}
}
