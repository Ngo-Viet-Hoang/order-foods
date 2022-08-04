package com.example.orderfood.service;

import javax.servlet.http.HttpServlet;

public class TelegramBotWebhook extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        String postInfo = Utils.getPostInfo(req, true);
//        if (postInfo.equals("")) {
//            RestResponce.error(RestError.POST_INFO_NULL, resp);
//            return;
//        }
//        JSONObject json = new JSONObject();
//        try {
//            json = new JSONObject(postInfo);
//
//
//            String token = req.getParameter("token");
//            if (!TelegramBot.TOKEN_WEBHOOK.equals(token)) {
//                RestResponce.error(RestError.PERMISSION_DENIED, resp);
//                return;
//            }
//
//            System.out.println(json.toString());
//            JSONObject message = json.getJSONObject("message");
//            JSONObject from = message.getJSONObject("from");
//
//            String userName = from.getString("username");
//            long chat_id = from.getLong("id");
//            String text = "Hi " + userName + ". Your chat_id is " + chat_id;
//            TelegramBot.sendMessage(chat_id, text);
//
//        } catch (Exception e) {
//            RestResponce.error(RestError.POST_INFO_INVALID, resp);
//            return;
//        }
//
//        RestResponce.success("{\"status\": 200}", resp);
//        return;
//    }
}
