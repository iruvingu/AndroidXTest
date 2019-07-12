package com.example.androidxtest.module

import com.example.androidxtest.repository.HelloRepo
import com.example.androidxtest.repository.HelloRepoImpl
import com.example.androidxtest.repository.MessagesRepo
import com.example.androidxtest.repository.MessagesRepoImpl
import com.example.androidxtest.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // single Instance of HelloRepo
    single<HelloRepo> { HelloRepoImpl() }

    factory<MessagesRepo> { MessagesRepoImpl() }

    viewModel { MainActivityViewModel(get(), get()) }

}