package com.yonder.exercise.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.orhanobut.hawk.Hawk;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.ui.favorites.FavoritesViewModel;
import com.yonder.exercise.ui.favorites.IFavoritesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YusufMac on 29/05/17.
 */


@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }


    @Provides
    @Singleton
    IFavoritesRepository providesEventRepository() {
        return new FavoritesViewModel(application);
    }

    @Provides
    @Singleton
    AppDatabase providesAppDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "books_db").build();
    }




}
