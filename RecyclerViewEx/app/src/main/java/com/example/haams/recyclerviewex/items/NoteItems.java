package com.example.haams.recyclerviewex.items;

/**
 * Created by haams on 2017-07-21.
 */

public class NoteItems {
    private String title;
    private String contents;

    public NoteItems(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
