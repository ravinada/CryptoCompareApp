package com.ravinada.cryptocompare.room;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FavouriteCoinDao_Impl implements FavouriteCoinDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FavouriteCoin> __insertionAdapterOfFavouriteCoin;

  private final EntityDeletionOrUpdateAdapter<FavouriteCoin> __deletionAdapterOfFavouriteCoin;

  private final SharedSQLiteStatement __preparedStmtOfUpdateData;

  public FavouriteCoinDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavouriteCoin = new EntityInsertionAdapter<FavouriteCoin>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `favourite_coins` (`tag`,`name`,`imageUrl`,`currentCoinPrice`,`rateChg`,`marketCap`,`totalVolume24h`,`directVolume24h`,`open24h`,`directVolumeSigned`,`lowHigh`,`check`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavouriteCoin value) {
        if (value.getTag() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTag());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getImageUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getImageUrl());
        }
        if (value.getCurrentCoinPrice() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCurrentCoinPrice());
        }
        if (value.getRateChg() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRateChg());
        }
        if (value.getMarketCap() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMarketCap());
        }
        if (value.getTotalVolume24h() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTotalVolume24h());
        }
        if (value.getDirectVolume24h() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDirectVolume24h());
        }
        if (value.getOpen24h() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getOpen24h());
        }
        if (value.getDirectVolumeSigned() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getDirectVolumeSigned());
        }
        if (value.getLowHigh() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLowHigh());
        }
        final Integer _tmp;
        _tmp = value.getCheck() == null ? null : (value.getCheck() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, _tmp);
        }
      }
    };
    this.__deletionAdapterOfFavouriteCoin = new EntityDeletionOrUpdateAdapter<FavouriteCoin>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `favourite_coins` WHERE `tag` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavouriteCoin value) {
        if (value.getTag() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTag());
        }
      }
    };
    this.__preparedStmtOfUpdateData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE favourite_coins SET name =?,imageUrl =?,currentCoinPrice =?,rateChg =?,marketCap =?,totalVolume24h =?,directVolume24h =?,open24h =?,directVolumeSigned =?,lowHigh =? WHERE tag=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final FavouriteCoin coin) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavouriteCoin.insert(coin);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final FavouriteCoin coin) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfFavouriteCoin.handle(coin);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateData(final String tag, final String newName, final String newImageUrl,
      final String newCurrentCoinPrice, final String newRateChg, final String newMarketCap,
      final String newTotalVolume24h, final String newDirectVolume24h, final String newOpen24h,
      final String newDirectVolumeSigned, final String newLowHigh) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateData.acquire();
    int _argIndex = 1;
    if (newName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newName);
    }
    _argIndex = 2;
    if (newImageUrl == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newImageUrl);
    }
    _argIndex = 3;
    if (newCurrentCoinPrice == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newCurrentCoinPrice);
    }
    _argIndex = 4;
    if (newRateChg == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newRateChg);
    }
    _argIndex = 5;
    if (newMarketCap == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newMarketCap);
    }
    _argIndex = 6;
    if (newTotalVolume24h == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newTotalVolume24h);
    }
    _argIndex = 7;
    if (newDirectVolume24h == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newDirectVolume24h);
    }
    _argIndex = 8;
    if (newOpen24h == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newOpen24h);
    }
    _argIndex = 9;
    if (newDirectVolumeSigned == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newDirectVolumeSigned);
    }
    _argIndex = 10;
    if (newLowHigh == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newLowHigh);
    }
    _argIndex = 11;
    if (tag == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, tag);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateData.release(_stmt);
    }
  }

  @Override
  public List<FavouriteCoin> getFavouriteCoins() {
    final String _sql = "SELECT * FROM favourite_coins ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTag = CursorUtil.getColumnIndexOrThrow(_cursor, "tag");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfCurrentCoinPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentCoinPrice");
      final int _cursorIndexOfRateChg = CursorUtil.getColumnIndexOrThrow(_cursor, "rateChg");
      final int _cursorIndexOfMarketCap = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCap");
      final int _cursorIndexOfTotalVolume24h = CursorUtil.getColumnIndexOrThrow(_cursor, "totalVolume24h");
      final int _cursorIndexOfDirectVolume24h = CursorUtil.getColumnIndexOrThrow(_cursor, "directVolume24h");
      final int _cursorIndexOfOpen24h = CursorUtil.getColumnIndexOrThrow(_cursor, "open24h");
      final int _cursorIndexOfDirectVolumeSigned = CursorUtil.getColumnIndexOrThrow(_cursor, "directVolumeSigned");
      final int _cursorIndexOfLowHigh = CursorUtil.getColumnIndexOrThrow(_cursor, "lowHigh");
      final int _cursorIndexOfCheck = CursorUtil.getColumnIndexOrThrow(_cursor, "check");
      final List<FavouriteCoin> _result = new ArrayList<FavouriteCoin>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final FavouriteCoin _item;
        final String _tmpTag;
        _tmpTag = _cursor.getString(_cursorIndexOfTag);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpImageUrl;
        _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        final String _tmpCurrentCoinPrice;
        _tmpCurrentCoinPrice = _cursor.getString(_cursorIndexOfCurrentCoinPrice);
        final String _tmpRateChg;
        _tmpRateChg = _cursor.getString(_cursorIndexOfRateChg);
        final String _tmpMarketCap;
        _tmpMarketCap = _cursor.getString(_cursorIndexOfMarketCap);
        final String _tmpTotalVolume24h;
        _tmpTotalVolume24h = _cursor.getString(_cursorIndexOfTotalVolume24h);
        final String _tmpDirectVolume24h;
        _tmpDirectVolume24h = _cursor.getString(_cursorIndexOfDirectVolume24h);
        final String _tmpOpen24h;
        _tmpOpen24h = _cursor.getString(_cursorIndexOfOpen24h);
        final String _tmpDirectVolumeSigned;
        _tmpDirectVolumeSigned = _cursor.getString(_cursorIndexOfDirectVolumeSigned);
        final String _tmpLowHigh;
        _tmpLowHigh = _cursor.getString(_cursorIndexOfLowHigh);
        final Boolean _tmpCheck;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfCheck)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfCheck);
        }
        _tmpCheck = _tmp == null ? null : _tmp != 0;
        _item = new FavouriteCoin(_tmpTag,_tmpName,_tmpImageUrl,_tmpCurrentCoinPrice,_tmpRateChg,_tmpMarketCap,_tmpTotalVolume24h,_tmpDirectVolume24h,_tmpOpen24h,_tmpDirectVolumeSigned,_tmpLowHigh,_tmpCheck);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Boolean getCoinExistance(final String tag) {
    final String _sql = "SELECT `check` FROM favourite_coins WHERE tag = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (tag == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, tag);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Boolean _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(0);
        }
        _result = _tmp == null ? null : _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
