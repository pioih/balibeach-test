package com.example.darbaitestbot.controller;

import com.example.darbaitestbot.bot.DarbaiBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final DarbaiBot bot;

    public WebHookController(DarbaiBot bot) {
        this.bot = bot;
    }

    @PostMapping("/")
    public BotApiMethod<?> accept(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }
}
