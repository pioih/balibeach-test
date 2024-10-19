package com.example.darbaitestbot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Trains {
    public InlineKeyboardMarkup getInlineMessageButtons() {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getButton("20.10 17:30"));
        row1.add(getButton("20.10 19:00"));
        row1.add(getButton("20.10 20.30"));
        rowList.add(row1);
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(getButton("21.10 17:30"));
        row2.add(getButton("21.10 19:00"));
        row2.add(getButton("21.10 20.30"));
        rowList.add(row2);
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(getButton("21.10 17:30"));
        row3.add(getButton("21.10 19:00"));
        row3.add(getButton("21.10 20.30"));
        rowList.add(row3);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardButton getButton(String buttonName) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonName);
        button.setCallbackData(buttonName);
        return button;
    }
}
