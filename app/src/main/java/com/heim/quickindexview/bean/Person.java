package com.heim.quickindexview.bean;

import com.heim.quickindexview.util.PinyinUtils;

/**
 * Created by zxp on 2016/8/9 0009.
 */
public class Person implements Comparable<Person>{
    public String name;
    public String pinyin; //名字拼音
    public String letter; //名字首字母

    public Person(String name) {
        this.name = name;
        this.pinyin = PinyinUtils.getPinyin(name);
        this.letter = pinyin.charAt(0)+"";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public int compareTo(Person another) {
        return this.pinyin.compareTo(another.pinyin);
    }
}
