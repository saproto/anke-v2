package nl.saproto.anke.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Query("select * from user limit 1")
    User loadUser();

    @Insert(onConflict = REPLACE)
    void insertUser(User user);

    @Query("delete from user")
    void deleteUser();
}
