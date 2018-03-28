package com.hjr;

import org.springframework.stereotype.Component;

/**
 * Created by Admin on 2017/12/5.
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
