package nl.saproto.anke.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrderlineDao {
    @Query("select * from orderline")
    List<Activity> getAll();

    @Query("SELECT * FROM orderline WHERE orderline_id LIKE :orderline LIMIT 1")
    Activity findByID(String orderline);

    @Insert(onConflict = REPLACE)
    void insertActivity(Orderline orderline);

    @Query("delete from orderline")
    void deleteOrderline();
}
