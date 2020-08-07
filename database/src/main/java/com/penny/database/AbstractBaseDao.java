package com.penny.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Onkar Chopade.
 */

@SuppressWarnings("ALL")
public abstract class AbstractBaseDao<T> {

  @Insert()
  public abstract long insert(T v);

  @Insert()
  public abstract long[] insert(T[] v);

  @Insert()
  public abstract List<Long> insert(List<T> v);

  @Update
  public abstract int update(T v);

  @Update
  public abstract int update(List<T> v);

  @Delete
  public abstract void delete(T v);

  @RawQuery
  protected abstract int doDeleteAll(SupportSQLiteQuery pQuery);

  @RawQuery
  public abstract int clearTableSequence(SimpleSQLiteQuery simpleQuery);

  @RawQuery
  protected abstract List<T> doGetAll(SupportSQLiteQuery pQuery);

  @RawQuery
  protected abstract T doGet(SupportSQLiteQuery pQuery);

  /**
   * Delete all rows from table
   */
  public int deleteAll() {
    clearSequence();
    return doDeleteAll(new SimpleSQLiteQuery("DELETE from " + getTableName()));
  }

  public void clearSequence() {
    clearTableSequence(new SimpleSQLiteQuery("delete from sqlite_sequence where name = '"+ getTableName() + "'"));
  }

  /**
   * Get all rows from table.
   */
  public List<T> get() {
    return doGetAll(new SimpleSQLiteQuery("select * from " + getTableName()));
  }

  /**
   * Get matching rows from table.
   */
  public T get(Integer pId) {
    return doGet(new SimpleSQLiteQuery(
        "select * from " + getTableName() + " where id = ?", new Object[]{pId}));
  }

  /**
   * Get matching rows from table.
   */
  public T get(Long pId) {
    return doGet(new SimpleSQLiteQuery(
        "select * from " + getTableName() + " where id = ?", new Object[]{pId}));
  }

  /**
   * Get matching rows from table.
   */
  public List<T> getListById(String pIdColumnName, Integer pId) {
    return doGetAll(new SimpleSQLiteQuery(
        "select * from " + getTableName() + " where " + pIdColumnName + " = ?", new Object[]{pId}));
  }

  /**
   * Get matching rows from table.
   */
  public List<T> getListById(String pIdColumnName, Long pId) {
    return doGetAll(new SimpleSQLiteQuery(
        "select * from " + getTableName() + " where " + pIdColumnName + " = ?", new Object[]{pId}));
  }

  /**
   * Get matching rows from table
   */
  public T get(String pId) {
    return doGet(new SimpleSQLiteQuery(
        "select * from " + getTableName() + " where id = ?", new Object[]{pId}));
  }

  /**
   * Get table name from Class
   *
   * @return String name of class adding underscore to capital letters for matching table name
   */
  @SuppressWarnings("ConstantConditions")
  private String getTableName() {
    Class clazz = (Class) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass())
        .getActualTypeArguments()[0];
    return clazz.getSimpleName();
  }
}
