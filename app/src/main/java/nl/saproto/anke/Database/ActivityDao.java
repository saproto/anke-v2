package nl.saproto.anke.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ActivityDao {
    @Query("select * from activity")
    List<Activity> getall();

    @Query("SELECT * FROM activity WHERE activity_name LIKE :name LIMIT 1")
    Activity findByName(String name);

    @Insert(onConflict = REPLACE)
    void insertActivity(Activity activity);

    @Query("delete from activity")
    void deleteActivity();
}
