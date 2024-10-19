package com.example.darbaitestbot.bot;

import com.example.darbaitestbot.models.MainMenu;
import com.example.darbaitestbot.service.Rent;
import com.example.darbaitestbot.service.Trains;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.Close;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DarbaiBot extends SpringWebhookBot {
    private final String token;
    private final String botName;
    private final String path;
    private final Trains trains;
    private final Rent rent;

    public DarbaiBot(SetWebhook setWebhook,
                     @Value("${token}") String token,
                     @Value("${path}") String path,
                     @Value("${botName}") String botName,
                     Trains trains, Rent rent) {
        super(setWebhook, path);
        this.botName = botName;
        this.token = token;
        this.path = path;
        this.trains = trains;
        this.rent = rent;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update != null) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message receivedMessage = update.getMessage();
            if (callbackQuery != null) {
                System.out.println(callbackQuery.getFrom().getLanguageCode());
                System.out.println(callbackQuery.getData());
                System.out.println(callbackQuery.getFrom());
                System.out.println(callbackQuery.getChatInstance());
                AnswerCallbackQuery answer = new AnswerCallbackQuery(callbackQuery.getId());
                answer.setText("не работает");
                return new SendMessage(callbackQuery.getFrom().getId().toString(), "не работает");
//                return answer;
            } else if (receivedMessage != null && receivedMessage.getText() != null) {
                if (receivedMessage.getText().equals(MainMenu.TRAIN_SIGN_UP.getText())) {
                    SendMessage message = new SendMessage(receivedMessage.getChatId().toString(), "Выберите дату и время");
                    message.setReplyMarkup(trains.getInlineMessageButtons());
                    return message;
                } else if (receivedMessage.getText().equals(MainMenu.COURT_RENT.getText())) {
                    SendMessage message = new SendMessage(receivedMessage.getChatId().toString(), "Выберите дату и время");
                    message.setReplyMarkup(rent.getInlineMessageButtons());
                    return message;
                } else if (receivedMessage.getText().equals(MainMenu.TOURNAMENT.getText())) {
                    return new SendMessage(receivedMessage.getChatId().toString(), "Ты еще не готов, иди тренируйся");
                }  else {
                    SendMessage message = new SendMessage(receivedMessage.getChatId().toString(), "Добро пожаловать");
                    message.setReplyMarkup(getMainMenuKeyboard());
                    return message;
                }
            }
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return path;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        Arrays.stream(MainMenu.values()).forEach(menu -> {
            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton(menu.getText()));
            keyboard.add(row);
        });

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}
