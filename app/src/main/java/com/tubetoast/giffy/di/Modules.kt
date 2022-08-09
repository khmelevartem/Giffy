package com.tubetoast.giffy.di

import com.tubetoast.giffy.data.CachedDataSource
import com.tubetoast.giffy.data.DataSource
import com.tubetoast.giffy.data.SearchRepositoryImpl
import com.tubetoast.giffy.data.StackDataStorage
import com.tubetoast.giffy.data.history.HistoryRepositoryImpl
import com.tubetoast.giffy.data.history.SearchHistoryDatabase
import com.tubetoast.giffy.data.history.SearchHistoryRoomConverter
import com.tubetoast.giffy.data.history.SearchHistoryStorage
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
import com.tubetoast.giffy.presentation.view.MainActivity
import com.tubetoast.giffy.presentation.viewmodel.ContentFragmentViewModel
import com.tubetoast.giffy.presentation.viewmodel.SearchViewModel
import com.tubetoast.giffy.utils.ConnectionChecker
import com.tubetoast.giffy.utils.CoroutineDispatchers
import com.tubetoast.giffy.utils.CoroutineDispatchersImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module


val appModule = module {
    viewModel { ContentFragmentViewModel(get<SearchInteractor>()) }
    viewModel { SearchViewModel(get<SearchInteractor>()) }
    single { GifPreviewAdapter() }
}

private val real = named("real")
private val mock = named("mock")

val searchModule = module {

    single<SearchInteractor> { SearchInteractorImpl(get<HistoryInteractor>(), get<SearchRepository>()) }
    single<SearchRepository> {
        SearchRepositoryImpl(
            get<DataSource<SearchRequest, SearchResult>>(real),
            get<CachedDataSource<SearchRequest, SearchResult>>(),
            get<CoroutineDispatchers>()
        )
    }
    single<DataSource<SearchRequest, SearchResult>>(real) {
        NetworkDataSource(
            GiphyApiProvider().api,
            get<ConnectionChecker>(),
            SearchResultApiConverter())
    }
    single<DataSource<SearchRequest, SearchResult>>(mock) { MockNetworkDataSource() }
    single { SearchResultDatabase.create(androidContext()) }
    single<CachedDataSource<SearchRequest, SearchResult>> {
        LocalDataSource(
            get<SearchResultDatabase>(),
            SearchResultRoomConverter(),
            get<CoroutineDispatchers>()
        )
    }
}

val historyModule = module {
    single<HistoryInteractor> { HistoryInteractorImpl(get<HistoryRepository>()) }
    single<HistoryRepository> {
        HistoryRepositoryImpl(
            get<StackDataStorage<SearchRequest>>(),
            get<CoroutineDispatchers>()
        )
    }
    single<StackDataStorage<SearchRequest>> {
        SearchHistoryStorage(
            get<SearchHistoryDatabase>(),
            SearchHistoryRoomConverter()
        )
    }
    single { SearchHistoryDatabase.create(androidContext()) }

}

val utilsModule = module {
    single<CoroutineDispatchers> { CoroutineDispatchersImpl() }
    single { ConnectionChecker(androidContext()) }
}