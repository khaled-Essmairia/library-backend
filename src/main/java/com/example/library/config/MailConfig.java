package com.example.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.example.library.category.Category;
import com.example.library.category.ICategoryDao;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("your-smtp-host"); // Set your SMTP host
        mailSender.setPort(587); // Set your SMTP port
        mailSender.setUsername("your-username"); // Set your SMTP username
        mailSender.setPassword("your-password"); // Set your SMTP password
        
        // You can also configure additional properties, such as TLS settings, etc.
        
        return mailSender;
    }
    
    @Component
    public class DatabaseLoader implements CommandLineRunner {

        private final ICategoryDao categoryRepository;

        @Autowired
        public DatabaseLoader(ICategoryDao categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            // Insert your SQL statements here using EntityManager or JpaRepository
            // For example:
            categoryRepository.save(new Category("INF", "Informatique"));
            categoryRepository.save(new Category("MAT", "Mathématiques, Physiques et Chimie"));
            // ... repeat for other categories
        }
}
    

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.library")) // Change to your package
                .paths(PathSelectors.any())
                .build()
                .host("localhost:8080/example.org"); // Set your hostname and port here
    }

    }

