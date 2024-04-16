package com.hlb.principle;

import com.hlb.principle.beanlife.BeanLifeComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test20240416ApplicationTests {

    @Autowired
    private BeanLifeComponent beanLifeComponent;
    @Test
    void contextLoads() {
        beanLifeComponent.use();
    }

}
