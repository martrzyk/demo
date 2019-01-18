package com.sparrowhawkmobile.jamdemo.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


fun <T> Single<T>.applyIOSubscribeMainThreadObserver(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applyIOSubscribeMainThreadObserver(): Completable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable = apply { compositeDisposable.add(this) }


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}