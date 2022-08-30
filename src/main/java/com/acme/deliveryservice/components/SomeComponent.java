package com.acme.deliveryservice.components;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import nz.net.ultraq.thymeleaf.layoutdialect.decorators.strategies.GroupingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class SomeComponent {
    public static final Logger logger = LoggerFactory.getLogger(SomeComponent.class);


    public SomeComponent(DataSource dataSource) throws SQLException {
        logger.info("Database connection valid = {}", dataSource.getConnection().isValid(1000));
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect(new GroupingStrategy());
    }

}