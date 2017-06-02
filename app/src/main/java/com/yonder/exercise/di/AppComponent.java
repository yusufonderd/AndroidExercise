package com.yonder.exercise.di;

import com.yonder.exercise.ui.books.BooksViewModel;
import com.yonder.exercise.ui.detail.BookDetailViewModel;
import com.yonder.exercise.ui.favorites.FavoritesViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by YusufMac on 29/05/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(BooksViewModel model);
    void inject(BookDetailViewModel model);

    void inject(FavoritesViewModel model);

}
