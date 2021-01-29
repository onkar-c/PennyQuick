package com.penny.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.penny.database.dao.BankDetailsDao;
import com.penny.database.dao.DisputeDao;
import com.penny.database.dao.OperatorsDao;
import com.penny.database.dao.RecentRechargeDao;
import com.penny.database.dao.RecipientDao;
import com.penny.database.dao.ReportDao;
import com.penny.database.dao.StateDao;
import com.penny.database.dao.UserEntityDao;
import com.penny.database.entities.BankDetails;
import com.penny.database.entities.Dispute;
import com.penny.database.entities.Operators;
import com.penny.database.entities.RecentRecharge;
import com.penny.database.entities.Recipient;
import com.penny.database.entities.Report;
import com.penny.database.entities.State;
import com.penny.database.entities.User;
import net.sqlcipher.database.SupportFactory;

@Database(
    entities = {
        User.class, RecentRecharge.class, Operators.class, State.class, Report.class,
        BankDetails.class, Recipient.class, Dispute.class
    },
    version = 1,
    exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

  private static AppDatabase INSTANCE;

  public static AppDatabase getInstance() {
    String pass = "passProtected";
    final SupportFactory factory = new SupportFactory(pass.getBytes());
    if (INSTANCE == null) {
      INSTANCE =
          Room.databaseBuilder(APP.getContext(), AppDatabase.class, "PennyQuick.db")
              .fallbackToDestructiveMigration()
              .openHelperFactory(factory)
              .build();
    }
    return INSTANCE;
  }

  public abstract UserEntityDao getUserEntityDao();

  public abstract RecentRechargeDao getRecentRechargeEntityDao();

  public abstract OperatorsDao getOperatorsDao();

  public abstract StateDao getStatesDao();

  public abstract ReportDao getReportDao();

  public abstract BankDetailsDao getBankDetailsDao();

  public abstract RecipientDao getRecipientDao();

  public abstract DisputeDao getDisputeDao();
}
