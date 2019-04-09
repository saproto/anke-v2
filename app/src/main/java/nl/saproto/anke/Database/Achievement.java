package nl.saproto.anke.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;


@Entity
public class Achievement {
    @PrimaryKey
    @NonNull
    public int achievement_id;

    public String achievement_name;
    public String description;
    public String icon;
    public String tier;

}
