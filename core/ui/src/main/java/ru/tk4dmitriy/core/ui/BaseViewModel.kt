package ru.tk4dmitriy.core.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class ViewModelMVI<INTENT : Intent, STATE : State, EFFECT : Effect> : ViewModel() {
    protected val mState: BehaviorSubject<STATE> = BehaviorSubject.create()
    val state: Observable<STATE> = mState.hide()

    protected val mEffect: PublishSubject<EFFECT> = PublishSubject.create()
    val effect: Observable<EFFECT> = mEffect.hide()

    fun dispatchIntent(intent: INTENT) {
        handleIntent(intent)
    }

    protected abstract fun handleIntent(intent: INTENT)
}