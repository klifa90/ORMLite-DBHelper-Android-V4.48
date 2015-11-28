package com.klifa.utils;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper {

	private RuntimeExceptionDao<Client, String> clienteRuntimeDao = null;
	
	private static final String DATABASE_NAME = "mydatabasename.db";
	private static final int DATABASE_VERSION = 1;
	
	public DBHelper(Context context) {
	   super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DBHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Client.class);

		} catch (SQLException e) {
			Log.e(DBHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		try {
			Log.i(DBHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Client.class, true);

			
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DBHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}
	
		
	@Override
	public void close() {
		super.close();
		clienteRuntimeDao = null;
	}
	
	public RuntimeExceptionDao<Client, String> getClienteDao(){ 
	     if (clienteRuntimeDao == null) { 
	    	 clienteRuntimeDao = getRuntimeExceptionDao(Cliente.class); 
	     } 
	     return clienteRuntimeDao; 
	 }
	
	

	
}
