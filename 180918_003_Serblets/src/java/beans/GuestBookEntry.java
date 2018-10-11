/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Manuel
 */
public class GuestBookEntry {
    private String nickname;
    private String comment;
    private LocalDate date;
    
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMM.yyyy", Locale.US);

    public GuestBookEntry(String nickname, String comment) {
        this.nickname = nickname;
        this.comment = comment;
        this.date = LocalDate.now();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }    
    
    public String toHTMLString()
    {
        return String.format("<i>%s</i> says: <br>%s<br>%s<br><br>", nickname,comment,dtf.format(date));
    }
}
