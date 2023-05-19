// StoreItem
package nazenov.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

public class StoreItem implements Parcelable {
    private String name;
    private int price;
    private boolean purchased;

    public StoreItem(String name, int price, boolean purchased) {
        this.name = name;
        this.price = price;
        this.purchased = purchased;
    }

    protected StoreItem(Parcel in) {
        name = in.readString();
        price = in.readInt();
        purchased = in.readByte() != 0;
    }

    public static final Creator<StoreItem> CREATOR = new Creator<StoreItem>() {
        @Override
        public StoreItem createFromParcel(Parcel in) {
            return new StoreItem(in);
        }

        @Override
        public StoreItem[] newArray(int size) {
            return new StoreItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeByte((byte) (purchased ? 1 : 0));
    }
}
