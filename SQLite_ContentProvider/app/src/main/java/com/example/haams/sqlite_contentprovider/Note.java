package com.example.haams.sqlite_contentprovider;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haams on 2017-07-18.
 */

public class Note implements Parcelable{
    long _id;
    String name;
    String word;
    int date;

    protected Note(Parcel in) {
        _id = in.readLong();
        name = in.readString();
        word = in.readString();
        date = in.readInt();
    }

    public Note(long _id, String name, String word, int date) {
        this._id = _id;
        this.name = name;
        this.word = word;
        this.date = date;
    }


    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeString(name);
        dest.writeString(word);
        dest.writeInt(date);
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", word='" + word + '\'' +
                ", date=" + date +
                '}';
    }
}
