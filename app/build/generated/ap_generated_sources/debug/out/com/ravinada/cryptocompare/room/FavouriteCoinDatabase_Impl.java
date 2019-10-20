package com.ravinada.cryptocompare.room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FavouriteCoinDatabase_Impl extends FavouriteCoinDatabase {
  private volatile FavouriteCoinDao _favouriteCoinDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `favourite_coins` (`tag` TEXT NOT NULL, `name` TEXT, `imageUrl` TEXT, `currentCoinPrice` TEXT, `rateChg` TEXT, `marketCap` TEXT, `totalVolume24h` TEXT, `directVolume24h` TEXT, `open24h` TEXT, `directVolumeSigned` TEXT, `lowHigh` TEXT, `check` INTEGER, PRIMARY KEY(`tag`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '168d5d74e29d77bcb6ac05c1ee920b02')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `favourite_coins`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFavouriteCoins = new HashMap<String, TableInfo.Column>(12);
        _columnsFavouriteCoins.put("tag", new TableInfo.Column("tag", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("currentCoinPrice", new TableInfo.Column("currentCoinPrice", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("rateChg", new TableInfo.Column("rateChg", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("marketCap", new TableInfo.Column("marketCap", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("totalVolume24h", new TableInfo.Column("totalVolume24h", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("directVolume24h", new TableInfo.Column("directVolume24h", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("open24h", new TableInfo.Column("open24h", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("directVolumeSigned", new TableInfo.Column("directVolumeSigned", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("lowHigh", new TableInfo.Column("lowHigh", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteCoins.put("check", new TableInfo.Column("check", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavouriteCoins = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavouriteCoins = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavouriteCoins = new TableInfo("favourite_coins", _columnsFavouriteCoins, _foreignKeysFavouriteCoins, _indicesFavouriteCoins);
        final TableInfo _existingFavouriteCoins = TableInfo.read(_db, "favourite_coins");
        if (! _infoFavouriteCoins.equals(_existingFavouriteCoins)) {
          return new RoomOpenHelper.ValidationResult(false, "favourite_coins(com.ravinada.cryptocompare.room.FavouriteCoin).\n"
                  + " Expected:\n" + _infoFavouriteCoins + "\n"
                  + " Found:\n" + _existingFavouriteCoins);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "168d5d74e29d77bcb6ac05c1ee920b02", "163d3d74d38750f85c7e335d6afd4265");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "favourite_coins");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `favourite_coins`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FavouriteCoinDao favouriteCoinDao() {
    if (_favouriteCoinDao != null) {
      return _favouriteCoinDao;
    } else {
      synchronized(this) {
        if(_favouriteCoinDao == null) {
          _favouriteCoinDao = new FavouriteCoinDao_Impl(this);
        }
        return _favouriteCoinDao;
      }
    }
  }
}
