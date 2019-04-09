package nl.saproto.anke.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Committee {
    @PrimaryKey
    @NonNull
    public int commmitee_id;

    public String committee_name;
    public String description;
    public String role;
    public Boolean not_public;


}
