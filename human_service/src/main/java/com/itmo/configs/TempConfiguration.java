package com.itmo.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.pip.services",
        "com.pip.dao",
        "com.pip.configs.common"
})
public class TempConfiguration {
}
