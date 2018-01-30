package com.dmb.davidmariexamen.Models;

/**
 * Created by davidmari on 30/1/18.
 */

public class Cycle {

    String course,title,promotionYear,students;

    public Cycle(String course,String title,String promotionYear,String students){
        this.course = course;
        this.title = title;
        this.promotionYear = promotionYear;
        this.students = students;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPromotionYear() {
        return promotionYear;
    }

    public void setPromotionYear(String promotionYear) {
        this.promotionYear = promotionYear;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }
}
