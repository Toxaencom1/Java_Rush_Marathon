package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "???";
    public static final String TOKEN = "???";

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // Отобразим сообщение о начале игры - нужно взломать холодильник
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Hack refrigerator! +20 Glory", "step_1_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT, Map.of(
                    "Take sausage! +20 Glory", "step_2_btn",
                    "Take fish! +20 Glory", "step_2_btn",
                    "Throw off a jar of cucumbers! +20 Glory", "step_2_btn"
            ));
        }

        if (getMessageText().equals("/glory")){
            sendTextMessageAsync(String.valueOf(getUserGlory()));
        }

        if (getMessageText().equals("/help")) {
            sendTextMessageAsync("""
                    /help   to view help message
                    /start  to start welcome message
                    /glory  to view your glory count
                    """);
        }

        // взламываем робот-пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Hack robot! +30 Glory", "step_3_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendTextMessageAsync(STEP_4_TEXT, Map.of(
                    "Send robot to take the meal! +30 Glory", "step_4_btn",
                    "Ride the robot! +30 Glory", "step_4_btn_ride",
                    "Runaway from robot! +30 Glory", "step_4_btn"
            ));
        }

        // взламываем камеру Go-Pro
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            addUserGlory(30);
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Put and turn on Go-Pro! +40 Glory", "step_5_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_4_btn_ride")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Put and turn on Go-Pro! +40 Glory", "step_5_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_6_TEXT, Map.of(
                    "Record yard animals on camera! +40 Glory", "step_6_btn_film",
                    "Run on rooftops, shoot with a GoPro! +40 Glory", "step_6_btn",
                    "Use GoPro to ambush other cats! +40 Glory", "step_6_btn"
            ));
        }

        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Hack the computer password! +50 Glory", "step_7_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_6_btn_film")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Hack the computer password! +50 Glory", "step_7_btn"));
        }

        // взламываем компьютер
        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Go out into the yard", "step_8_btn"));
        }

        // хвастаемся дворовым котам
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
            sendTextMessageAsync(FINAL_TEXT);
            // Тут лучше использовать относительный путь от проекта
            sendImageMessageAsync("src/main/resources/images/final_pic.jpg");
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}