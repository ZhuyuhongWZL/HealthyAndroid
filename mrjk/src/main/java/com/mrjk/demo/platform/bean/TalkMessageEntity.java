package com.mrjk.demo.platform.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "talk_message", schema = "dh")
public class TalkMessageEntity implements Serializable{
    private int id;
    private String ffrom;
    private String tto;
    private String content;
    private Timestamp time;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ffrom")
    public String getFfrom() {
        return ffrom;
    }

    public void setFfrom(String ffrom) {
        this.ffrom = ffrom;
    }

    @Basic
    @Column(name = "tto")
    public String getTto() {
        return tto;
    }

    public void setTto(String tto) {
        this.tto = tto;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TalkMessageEntity that = (TalkMessageEntity) o;
        return id == that.id &&
                Objects.equals(ffrom, that.ffrom) &&
                Objects.equals(tto, that.tto) &&
                Objects.equals(content, that.content) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, ffrom, tto, content, time);
    }

}
