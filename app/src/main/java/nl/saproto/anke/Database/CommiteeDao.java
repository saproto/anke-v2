package nl.saproto.anke.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CommiteeDao {
    @Query("select * from committee")
    List<Committee> getall();

    @Query("SELECT * FROM committee WHERE committee_name LIKE :name LIMIT 1")
    Committee findByName(String name);

    @Insert(onConflict = REPLACE)
    void insertActivity(Committee committee);

    @Query("delete from committee")
    void deleteActivity();
}
