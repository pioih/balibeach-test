package com.example.darbaitestbot.models;

public enum MainMenu {
    TRAIN_SIGN_UP("Запись на занятие в академии"),
    COURT_RENT("Аренда площадки"),
    TOURNAMENT("Запись на турнир");

    private final String text;

    MainMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    static MainMenu getByText(String text) {
        for (MainMenu menu : MainMenu.values()) {
            if (menu.getText().equals(text)) {
                return menu;
            }
        }
        return null;
    }
}
