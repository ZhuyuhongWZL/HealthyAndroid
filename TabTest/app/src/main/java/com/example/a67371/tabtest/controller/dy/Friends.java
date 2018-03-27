package com.example.a67371.tabtest.controller.dy;

import java.util.List;

public class Friends {

    /**
     * id : 8
     * user : dy
     * doctor : hy
     * createTime : Mar 20, 2018 10:14:43 AM
     */
    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public static class Friend

    {


        private  int id;
        private  String user;
        private  String doctor;
        private  String createTime;

        public  int getId () {
            return id;
        }

        public  void setId ( int id){
            this.id = id;
        }

        public  String getUser () {
            return user;
        }

        public  void setUser (String user){
            this.user = user;
        }

        public  String getDoctor () {
            return doctor;
        }

        public  void setDoctor (String doctor){
            this.doctor = doctor;
        }

        public  String getCreateTime () {
            return createTime;
        }

        public  void setCreateTime (String createTime){
            this.createTime = createTime;
        }

    }
}
