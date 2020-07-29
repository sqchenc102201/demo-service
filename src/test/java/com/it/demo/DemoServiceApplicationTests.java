package com.it.demo;

import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class DemoServiceApplicationTests {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    void contextLoads() {
        String aa = IdUtil.simpleUUID();
        System.out.println(aa);
        System.out.println(IdUtil.randomUUID());
    }

}
