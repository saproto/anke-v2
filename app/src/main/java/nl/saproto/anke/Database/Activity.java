package nl.saproto.anke.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;


@Entity
public class Activity {
    @PrimaryKey
    @NonNull
    public int activity_id;
    public String activity_name;

    public Date date;
    public String description;
    public String organizedBy;
    public Boolean signedUp;

    public Date singUpOpens;
    public Date signUpCloses;
    public Date singOutPossible;

    public List<String> participants;
}
