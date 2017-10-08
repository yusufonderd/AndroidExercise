package com.yonder.exercise.shared.utils

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by YusufMac on 02/06/17.
 */

object ObservableUtil {
    fun completableObserver(completable: Completable) {
        completable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}
