package com.tubetoast.giffy.di

import com.tubetoast.giffy.data.DataSource
import com.tubetoast.giffy.data.GiphyApiProvider
import com.tubetoast.giffy.data.HistoryRepositoryImpl
import com.tubetoast.giffy.data.LocalDataSource
import com.tubetoast.giffy.data.NetworkDataSource
import com.tubetoast.giffy.data.SearchRepositoryImpl
import com.tubetoast.giffy.data.SearchResultEntityConverter
import com.tubetoast.giffy.domain.HistoryInteractor
import com.tubetoast.giffy.domain.HistoryInteractorImpl
import com.tubetoast.giffy.domain.HistoryRepository
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.domain.SearchInteractorImpl
import com.tubetoast.giffy.domain.SearchRepository
import com.tubetoast.giffy.presentation.view.GifPreviewAdapter
import com.tubetoast.giffy.presentation.viewmodel.SearchFragmentViewModel
import com.tubetoast.giffy.utils.ConnectionChecker
import com.tubetoast.giffy.utils.CoroutineDispatchers
import com.tubetoast.giffy.utils.CoroutineDispatchersImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val local = named("local")
private val network = named("network")

val appModule = module {
    viewModel { SearchFragmentViewModel(get()) }

    single<SearchInteractor> { SearchInteractorImpl(get(), get()) }
    single<SearchRepository> { SearchRepositoryImpl(get(network), get(local), get(), get()) }
    single<HistoryInteractor> { HistoryInteractorImpl(get()) }
    single<HistoryRepository> { HistoryRepositoryImpl() }

    single<DataSource>(network) { NetworkDataSource(GiphyApiProvider().api, get()) }
    single<DataSource>(local) { LocalDataSource() }
    single { SearchResultEntityConverter() }

    single<CoroutineDispatchers> { CoroutineDispatchersImpl() }

    single { ConnectionChecker(androidContext()) }

    single { GifPreviewAdapter() }
}