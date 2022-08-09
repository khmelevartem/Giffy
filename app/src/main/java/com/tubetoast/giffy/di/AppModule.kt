package com.tubetoast.giffy.di

import com.tubetoast.giffy.data.CachedDataSource
import com.tubetoast.giffy.data.DataSource
import com.tubetoast.giffy.data.HistoryRepositoryImpl
import com.tubetoast.giffy.data.SearchRepositoryImpl
import com.tubetoast.giffy.data.local.LocalDataSource
import com.tubetoast.giffy.data.local.SearchResultDatabase
import com.tubetoast.giffy.data.local.SearchResultRoomConverter
import com.tubetoast.giffy.data.network.GiphyApiProvider
import com.tubetoast.giffy.data.network.MockNetworkDataSource
import com.tubetoast.giffy.data.network.NetworkDataSource
import com.tubetoast.giffy.data.network.SearchResultApiConverter
import com.tubetoast.giffy.domain.HistoryInteractor
import com.tubetoast.giffy.domain.HistoryInteractorImpl
import com.tubetoast.giffy.domain.HistoryRepository
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.domain.SearchInteractorImpl
import com.tubetoast.giffy.domain.SearchRepository
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult
import com.tubetoast.giffy.presentation.view.GifPreviewAdapter
import com.tubetoast.giffy.presentation.viewmodel.SearchFragmentViewModel
import com.tubetoast.giffy.utils.ConnectionChecker
import com.tubetoast.giffy.utils.CoroutineDispatchers
import com.tubetoast.giffy.utils.CoroutineDispatchersImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val real = named("real")
val mock = named("mock")

val appModule = module {
    viewModel { SearchFragmentViewModel(get<SearchInteractor>()) }

    single<SearchInteractor> { SearchInteractorImpl(get<HistoryInteractor>(), get<SearchRepository>()) }
    single<SearchRepository> {
        SearchRepositoryImpl(
            get<DataSource<SearchRequest, SearchResult>>(mock),
            get<CachedDataSource<SearchRequest, SearchResult>>(),
            get<CoroutineDispatchers>()
        )
    }
    single<HistoryInteractor> { HistoryInteractorImpl(get<HistoryRepository>()) }
    single<HistoryRepository> { HistoryRepositoryImpl() }

    single<DataSource<SearchRequest, SearchResult>>(real) {
        NetworkDataSource(
            GiphyApiProvider().api,
            get<ConnectionChecker>(),
            get<SearchResultApiConverter>())
    }
    single<DataSource<SearchRequest, SearchResult>>(mock) { MockNetworkDataSource() }
    single<CachedDataSource<SearchRequest, SearchResult>> {
        LocalDataSource(
            get<SearchResultDatabase>(),
            get<SearchResultRoomConverter>()
        )
    }
    single { SearchResultDatabase.create(androidContext()) }
    single { SearchResultRoomConverter() }
    single { SearchResultApiConverter() }

    single<CoroutineDispatchers> { CoroutineDispatchersImpl() }

    single { ConnectionChecker(androidContext()) }

    single { GifPreviewAdapter() }
}