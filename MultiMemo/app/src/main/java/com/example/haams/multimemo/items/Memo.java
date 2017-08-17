package com.example.haams.multimemo.items;

/**
 * Created by haams on 2017-08-16.
 */

public class Memo {
    private String memoTitle;
    private String memoContent;
    private String memoImagePath;

    public Memo(String memoTitle, String memoContent, String memoImagePath) {
        this.memoTitle = memoTitle;
        this.memoContent = memoContent;
        this.memoImagePath = memoImagePath;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public String getMemoImagePath() {
        return memoImagePath;
    }
}
