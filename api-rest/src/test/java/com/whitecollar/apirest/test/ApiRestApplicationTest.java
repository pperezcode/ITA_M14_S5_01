package com.whitecollar.apirest.test;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.whitecollar.apirest.controller.FranquiciaController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApiRestApplicationTest {
	
	@Autowired
	FranquiciaController fController;

	@Test
	void contextLoads() {
		Assertions.assertThat(fController).isNotNull();
	}

}
