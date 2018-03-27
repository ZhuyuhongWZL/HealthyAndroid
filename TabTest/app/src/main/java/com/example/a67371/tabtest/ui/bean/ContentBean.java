package com.example.a67371.tabtest.ui.bean;

import java.util.List;

public class ContentBean {

    /**
     * success : true
     * messages : [{"from":"error","to":"error","content":"1","time":"2018-03-18 20:34:08.721"}]
     */

    private boolean success;
    private List<MessagesBean> messages;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<MessagesBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesBean> messages) {
        this.messages = messages;
    }

    public static class MessagesBean {
        /**
         * from : error
         * to : error
         * content : 1
         * time : 2018-03-18 20:34:08.721
         */

        private String from;
        private String to;
        private String content;
        private String time;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
