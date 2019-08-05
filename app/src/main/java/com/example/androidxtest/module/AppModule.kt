package com.example.androidxtest.module

import com.example.androidxtest.repository.*
import com.example.androidxtest.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // single Instance of HelloRepo
    single<HelloRepo> { HelloRepoImpl() }

    factory { MessagesRepoImpl() }

    factory<MessageRepoByCoroutines> { MessageRepoByCoroutinesImpl() }

    viewModel { MainActivityViewModel(get(), get(), get()) }

}