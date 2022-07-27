package com.example.giffy.di

import com.example.giffy.domain.SearchInteractor
import com.example.giffy.domain.SearchInteractorImpl
import com.example.giffy.presentation.MainActivityViewModel
import org.koin.dsl.module

val appModule = module {
    single { MainActivityViewModel(get()) }
    single<SearchInteractor> { SearchInteractorImpl() }
}