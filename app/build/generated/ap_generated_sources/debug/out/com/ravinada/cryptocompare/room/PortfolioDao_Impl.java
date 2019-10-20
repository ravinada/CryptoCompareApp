package com.ravinada.cryptocompare.room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PortfolioDao_Impl implements PortfolioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Portfolio> __insertionAdapterOfPortfolio;

  private final EntityDeletionOrUpdateAdapter<Portfolio> __deletionAdapterOfPortfolio;

  public PortfolioDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPortfolio = new EntityInsertionAdapter<Portfolio>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `portfolios` (`id`,`name`,`currency`,`description`,`access`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Portfolio value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getCurrency() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCurrency());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        stmt.bindLong(5, value.getAccess());
      }
    };
    this.__deletionAdapterOfPortfolio = new EntityDeletionOrUpdateAdapter<Portfolio>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `portfolios` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Portfolio value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void insert(final Portfolio portfolio) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPortfolio.insert(portfolio);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Portfolio portfolio) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPortfolio.handle(portfolio);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Portfolio>> getPortfolios() {
    final String _sql = "SELECT * FROM portfolios";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"portfolios"}, false, new Callable<List<Portfolio>>() {
      @Override
      public List<Portfolio> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAccess = CursorUtil.getColumnIndexOrThrow(_cursor, "access");
          final List<Portfolio> _result = new ArrayList<Portfolio>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Portfolio _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCurrency;
            _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final int _tmpAccess;
            _tmpAccess = _cursor.getInt(_cursorIndexOfAccess);
            _item = new Portfolio(_tmpName,_tmpCurrency,_tmpDescription,_tmpAccess);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
