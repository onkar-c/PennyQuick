package com.penny.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.penny.database.dao.RecentRechargeDao;
import com.penny.database.dao.UserEntityDao;
import com.penny.database.entities.RecentRecharge;
import com.penny.database.entities.User;

@Database(
    entities = {
        User.class, RecentRecharge.class
    },
    version = 1,
    exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

  private static AppDatabase INSTANCE;

  public static AppDatabase getInstance() {
    if (INSTANCE == null) {
      INSTANCE =
          Room.databaseBuilder(APP.getContext(), AppDatabase.class, "PennyQuick.db")
              .fallbackToDestructiveMigration()
              .build();
    }
    return INSTANCE;
  }

  public abstract UserEntityDao getUserEntityDao();

  public abstract RecentRechargeDao getRecentRechargeEntityDao();
}
