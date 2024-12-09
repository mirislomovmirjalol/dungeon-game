package com.game.dungeon.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI dungeonGameOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dungeon Game API")
                        .description("API for managing dungeon game, including game creation, player movement, and leaderboard")
                        .version("1.0"));
    }
} 