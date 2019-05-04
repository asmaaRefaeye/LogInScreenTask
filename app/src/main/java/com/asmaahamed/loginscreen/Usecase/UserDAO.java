package com.asmaahamed.loginscreen.Usecase;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.asmaahamed.loginscreen.Entity.User;

@Dao
public interface UserDAO  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);


    @Delete
     void delete(User users);

   @Query("SELECT * FROM users WHERE name =:name and password =:password")
    User getUserByName(String name , String password);


}
