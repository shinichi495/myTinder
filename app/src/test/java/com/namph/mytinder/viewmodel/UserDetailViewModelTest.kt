package com.namph.mytinder.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.namph.mytinder.base.BaseUTTest
import com.namph.mytinder.data.datasource.user.UserApiDataSource
import com.namph.mytinder.data.datasource.user.UserApiDataSourceImpl
import com.namph.mytinder.data.datasource.user.UserLocalDataSource
import com.namph.mytinder.data.repository.UserRepositoryImpl
import com.namph.mytinder.data.service.UserService
import com.namph.mytinder.di.configurationTestAppComponent
import com.namph.mytinder.domain.model.User
import com.namph.mytinder.domain.repository.UserRepository
import com.namph.mytinder.domain.usecase.base.Result
import com.namph.mytinder.domain.usecase.user.GetUserUseCase
import com.namph.mytinder.domain.usecase.user.SaveUserLocalUseCase
import com.namph.mytinder.model.UserItem
import com.namph.mytinder.presenter.feature.user.UserDetailViewModel
import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class UserDetailViewModelTest : BaseUTTest() {

    //Target
    private lateinit var mRepo: UserRepository

    //Inject api service created with koin
    val mAPIService: UserService by inject()

    //Inject Mockwebserver created with koin
    val mockWebServer: MockWebServer by inject()

    val repo: UserRepository by inject()
    lateinit var mockUserApiDataSource: UserApiDataSource
    val mockUserLocalDataSource = Mockito.mock(UserLocalDataSource::class.java)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun start() {
        super.setUp()
        startKoin {
            modules(configurationTestAppComponent(getMockWebServerUrl()))
        }
    }


    @Test
    fun `Get user from view model success`()  {
        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mockUserApiDataSource = UserApiDataSourceImpl(mAPIService)
        mRepo = UserRepositoryImpl(mockUserApiDataSource, mockUserLocalDataSource)

        val usecase = GetUserUseCase(mRepo)
        usecase.backgroundDispatcher = Dispatchers.Unconfined
        usecase.coroutineContext = SupervisorJob() + Dispatchers.Unconfined
        usecase.invoke(GetUserUseCase.GET_USER_SERVER)

        val vm = UserDetailViewModel(usecase,mock(SaveUserLocalUseCase::class.java))
        vm.coroutineContext = Job() + Dispatchers.Unconfined
        vm.requestUserFromServer()

    }
}