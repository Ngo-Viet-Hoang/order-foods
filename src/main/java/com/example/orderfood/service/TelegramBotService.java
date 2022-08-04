package com.example.orderfood.service;

import lombok.Builder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
@Builder
public class TelegramBotService {
    private static final String BOT_TOKEN = "5535724474:AAF-Lg-J4opfZ97H_dXML-UzUk44zn79AWQ"; //token
    public static final String TOKEN_WEBHOOK = "reyfherye";
    public static String adminTelegram = "5373396626"; //id dau bep
     public static void main(String[] args) throws Exception {
     // String webhook =
     //"https://hatforrent.com/api/public/v1/tele-bot-webhook?token=" +
     // TOKEN_WEBHOOK;
     // setWebhook(webhook);
         sendErrorToMe("fsafsafa");
     }

    public static void sendErrorToMe(String message) {
        sendMessage(adminTelegram + "", "" + message);
    }

    public static String sendMessage(long chat_id, String message) throws IOException {
        return sendMessage(chat_id + "", message);
    }

    public static String sendMessage(String chat_id, String message) {
        try {
            String url =
                    "https://api.telegram.org/bot"
                            + BOT_TOKEN
                            + "/sendMessage?chat_id="
                            + chat_id // id dau bep
                            + "&text="
                            + message;
            Document doc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).get();
            System.out.println(doc.body().text());
            return doc.body().text();
        } catch (Exception e) {
            return null;
        }
    }

    public static String setWebhook(String urlWebhook) throws IOException {
        String url = "https://api.telegram.org/bot" + BOT_TOKEN + "/setWebhook?url=" + urlWebhook;
        Document doc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).get();
        System.out.println(doc.body().text());
        return doc.body().text();
    }
}
