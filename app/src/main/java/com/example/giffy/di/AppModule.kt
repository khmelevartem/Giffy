package com.example.giffy.di

import com.example.giffy.data.CoroutineDispatchers
import com.example.giffy.data.CoroutineDispatchersImpl
import com.example.giffy.data.DataSource
import com.example.giffy.data.GiphyApiProvider
import com.example.giffy.data.LocalDataSource
import com.example.giffy.data.NetworkDataSource
import com.example.giffy.data.SearchRepositoryImpl
import com.example.giffy.data.SearchResultEntityConverter
import com.example.giffy.domain.HistoryInteractor
import com.example.giffy.domain.HistoryInteractorImpl
import com.example.giffy.domain.SearchInteractor
import com.example.giffy.domain.SearchInteractorImpl
import com.example.giffy.domain.SearchRepository
import com.example.giffy.presentation.MainActivityViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val local = named("local")
private val network = named("network")

val appModule = module {
    single { MainActivityViewModel(get()) }

    single<SearchInteractor> { SearchInteractorImpl(get(), get()) }
    single<HistoryInteractor> { HistoryInteractorImpl() }
    single<SearchRepository> { SearchRepositoryImpl(get(network), get(local), get(), get()) }

    single<DataSource>(network) { NetworkDataSource(GiphyApiProvider().api) }
    single<DataSource>(local) { LocalDataSource() }
    single { SearchResultEntityConverter() }

    single<CoroutineDispatchers> { CoroutineDispatchersImpl() }
}