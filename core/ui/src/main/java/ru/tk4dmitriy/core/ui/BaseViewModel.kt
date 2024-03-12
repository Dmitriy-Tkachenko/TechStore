package ru.tk4dmitriy.core.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class ViewModelMVI<INTENT : Intent, STATE : State> : ViewModel() {
    protected val mState: BehaviorSubject<STATE> = BehaviorSubject.create()
    val state: Observable<STATE> = mState

    fun dispatchIntent(intent: INTENT) {
        handleIntent(intent)
    }

    protected abstract fun handleIntent(intent: INTENT)
}