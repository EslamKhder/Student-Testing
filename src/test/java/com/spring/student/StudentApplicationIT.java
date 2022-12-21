package com.spring.student;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(Extension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentApplicationIT {

}
