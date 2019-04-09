package nl.saproto.anke.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String calling_id;

    public String calling_name;
    public String name;
    public String email;
    public String birthdate;
    public String phone;
    public Boolean phone_visible;

    //adres
    public String street;
    public String zipcode;
    public String city;
    public String country;
    public Boolean address_visible;



}
