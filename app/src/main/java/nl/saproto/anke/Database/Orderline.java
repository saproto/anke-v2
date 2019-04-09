package nl.saproto.anke.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;


@Entity
public class Orderline {
    @PrimaryKey
    @NonNull
    public int orderline_id;

    public String product_name;

    public Date date;
    public String price;
    public int amount;
}
