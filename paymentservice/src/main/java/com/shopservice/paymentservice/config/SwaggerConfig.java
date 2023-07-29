package com.shopservice.paymentservice.config;


import com.shopservice.paymentservice.constants.CommonConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        
        Contact contact = new Contact();
        contact.setEmail(CommonConstants.EMAIL);
        contact.setName(CommonConstants.NAME);

        Info info = new Info()
                .title("Product Service API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage product service ");


        return new OpenAPI().info(info);
    }
}
