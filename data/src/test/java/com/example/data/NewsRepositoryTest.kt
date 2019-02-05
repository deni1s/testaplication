package com.example.data

import com.example.data.news.NewsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NewsRepositoryTest {

    @Mock
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUP() {
        MockitoAnnotations.initMocks(this)
    }

    @Test //runBlockingSilent
    fun testForSuccess() = runBlocking<Unit> {
        Mockito.verify(newsRepository).getLinkTypeOrReturnInvalid(
            "https://newsapi.org/v2/everything?q=bitcoin&from=2019-02-01&sortBy=publishedAt&apiKey=2778ba9d0314458596009ce5650f906f"
        )
    }
}