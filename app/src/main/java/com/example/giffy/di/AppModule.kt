package com.example.giffy.di

import com.bumptech.glide.Glide
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
import com.example.giffy.presentation.view.GifPreviewAdapter
import com.example.giffy.presentation.viewmodel.SearchFragmentViewModel
import com.example.giffy.utils.ConnectionChecker
import com.example.giffy.utils.CoroutineDispatchers
import com.example.giffy.utils.CoroutineDispatchersImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val local = named("local")
private val network = named("network")

val appModule = module {
    viewModel { SearchFragmentViewModel(get()) }

    single<SearchInteractor> { SearchInteractorImpl(get(), get()) }
    single<HistoryInteractor> { HistoryInteractorImpl() }
    single<SearchRepository> { SearchRepositoryImpl(get(network), get(local), get(), get()) }

    single<DataSource>(network) { NetworkDataSource(GiphyApiProvider().api, get()) }
    single<DataSource>(local) { LocalDataSource() }
    single { SearchResultEntityConverter() }

    single<CoroutineDispatchers> { CoroutineDispatchersImpl() }

    single { ConnectionChecker(androidContext()) }

    single { GifPreviewAdapter() }
}