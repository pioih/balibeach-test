package com.example.darbaitestbot.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class Config {
    @Bean
    public SetWebhook setWebhook() {
        return new SetWebhook();
    }
}
