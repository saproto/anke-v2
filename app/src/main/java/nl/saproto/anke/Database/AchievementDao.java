package nl.saproto.anke.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface AchievementDao {
    @Query("select * from achievement")
    List<Achievement> getall();

    @Query("SELECT * FROM achievement WHERE achievement_name LIKE :name LIMIT 1")
    Achievement findByName(String name);

    @Insert(onConflict = REPLACE)
    void insertActivity(Achievement achievement);

    @Query("delete from activity")
    void deleteActivity();
}
