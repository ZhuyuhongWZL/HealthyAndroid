package com.mrjk.demo.platform.bean;

import java.util.List;

public class ResponseBean{

    /**
     * success : true
     * messages : [{"from":"hy","to":"hy1","content":"hello","time":"2018-03-13 13:39:37"}]
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


    public static class MessagesBean implements Comparable<MessagesBean>{
        /**
         * from : hy
         * to : hy1
         * content : hello
         * time : 2018-03-13 13:39:37
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

        @Override
        public int compareTo(MessagesBean o) {
            return this.time.compareTo(o.time);
        }
    }
}
