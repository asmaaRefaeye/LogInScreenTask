package com.asmaahamed.loginscreen.Usecase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.asmaahamed.loginscreen.Entity.User;

@Database(entities = {User.class},version = 1 , exportSchema = false)
public abstract class UserDataBase  extends RoomDatabase {

        public abstract UserDAO getUserDao();

}
