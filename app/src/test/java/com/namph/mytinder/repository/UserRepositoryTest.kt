package com.namph.mytinder.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.namph.mytinder.base.BaseUTTest
import com.namph.mytinder.data.datasource.user.UserApiDataSource
import com.namph.mytinder.data.datasource.user.UserApiDataSourceImpl
import com.namph.mytinder.data.datasource.user.UserLocalDataSource
import com.namph.mytinder.data.repository.UserRepositoryImpl
import com.namph.mytinder.data.service.UserService
import com.namph.mytinder.di.configurationTestAppComponent
import com.namph.mytinder.domain.repository.UserRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

import org.koin.test.inject
import org.mockito.Mockito.mock
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class UserRepositoryTest : BaseUTTest() {

    //Target
    private lateinit var mRepo: UserRepository

    //Inject api service created with koin
    val mAPIService: UserService by inject()

    //Inject Mockwebserver created with koin
    val mockWebServer: MockWebServer by inject()

    val repo: UserRepository by inject()

    lateinit var mockUserApiDataSource: UserApiDataSource
    val mockUserLocalDataSource = mock(UserLocalDataSource::class.java)

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
    fun `Get user from server success`() = runBlocking<Unit> {
        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mockUserApiDataSource = UserApiDataSourceImpl(mAPIService)
        mRepo = UserRepositoryImpl(mockUserApiDataSource, mockUserLocalDataSource)
        val data = mRepo.getUserInfor()
        Assert.assertNotNull(data)
        data.handleResult(
            {
                assertNotNull(it)
            }
        )
    }
}