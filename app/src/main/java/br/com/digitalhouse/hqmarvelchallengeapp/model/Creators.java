
package br.com.digitalhouse.hqmarvelchallengeapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Creators implements Parcelable {

    @Expose
    private Long available;
    @Expose
    private String collectionURI;
    @Expose
    private List<Item> items;
    @Expose
    private Long returned;

    protected Creators(Parcel in) {
        if (in.readByte() == 0) {
            available = null;
        } else {
            available = in.readLong();
        }
        collectionURI = in.readString();
        items = in.createTypedArrayList(Item.CREATOR);
        if (in.readByte() == 0) {
            returned = null;
        } else {
            returned = in.readLong();
        }
    }

    public static final Creator<Creators> CREATOR = new Creator<Creators>() {
        @Override
        public Creators createFromParcel(Parcel in) {
            return new Creators(in);
        }

        @Override
        public Creators[] newArray(int size) {
            return new Creators[size];
        }
    };

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getReturned() {
        return returned;
    }

    public void setReturned(Long returned) {
        this.returned = returned;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (available == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(available);
        }
        dest.writeString(collectionURI);
        dest.writeTypedList(items);
        if (returned == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(returned);
        }
    }
}
