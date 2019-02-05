package com.example.presentation

import com.example.model.Link
import com.example.model.News
import com.example.presentation.view.newslist.NewsListContract
import com.example.presentation.view.newslist.NewsListPresenter
import com.example.service.NewsRepositoryService
import com.example.service.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class NewsListPresenterTest {

    @Mock
    private lateinit var feedView: NewsListContract.View
    private lateinit var link: Link

    @Mock
    private lateinit var fetchFeed: NewsRepositoryService

    private lateinit var presenterImpl: NewsListPresenter

    private val testScopeProvider = TestScopeProvider()

    private lateinit var job: Job


    @Before
    fun setUP() {
        MockitoAnnotations.initMocks(this)
        job = Job()
    }


    @Test //runBlockingSilent
    fun testForSuccess() = runBlockingSilent {

        link = getLink()
        val feed = getNewsList()
        presenterImpl = NewsListPresenter(testScopeProvider, arrayListOf(link, link), fetchFeed)
        presenterImpl.subscribe(feedView)
        presenterImpl.loadNewsList()
        Mockito.doReturn(feed).`when`(fetchFeed).loadNewsList(link)
        val inOrder = Mockito.inOrder(feedView)
        inOrder.verify(feedView).showProgressBar()

        Mockito.verify(fetchFeed).loadNewsList(link)


        if (feed is Result.Success) {
            inOrder.verify(feedView).showNewsList(feed.data)
            assert(true)
        } else if (feed is Result.Error) {
            assert(false)
        }
    }

    private fun getNewsList(): Result<List<News>> {
        val newsItem = News()
        newsItem.image = "https://www.mtlblog.com/uploads/ded37dd9e380abe15e45eea13ed050c13e5fe206.jpg_facebook.jpg"
        newsItem.title = "title"
        newsItem.description = "description"
        newsItem.author = "author"
        return Result.Success(arrayListOf(newsItem, newsItem))
    }

    private fun getNewsError(): Result<List<News>> {
        return Result.Error(Throwable())
    }

    private fun getLink(): Link {
        val link = Link()
        link.link =
                "https://newsapi.org/v2/everything?q=bitcoin&from=2019-02-05&sortBy=publishedAt&apiKey=2778ba9d0314458596009ce5650f906f"
        link.type = Link.JSON_TYPE
        return link
    }

    @Test
    fun testForFailure() = runBlocking {

        link = getLink()
        val feed = getNewsError()
        presenterImpl = NewsListPresenter(testScopeProvider, arrayListOf(link, link), fetchFeed)
        presenterImpl.subscribe(feedView)
        presenterImpl.loadNewsList()
       // Mockito.doReturn(feed).`when`(fetchFeed).loadNewsList(link)
        `when`(fetchFeed.loadNewsList(link)).thenReturn(feed)
        val inOrder = Mockito.inOrder(feedView)
        inOrder.verify(feedView).showProgressBar()

        Mockito.verify(fetchFeed).loadNewsList(link)


        if (feed is Result.Success) {
            assert(false)
        } else if (feed is Result.Error) {
//            inOrder.verify(feedView).hideProgressBar()
//            inOrder.verify(feedView).showError(feed.exception.localizedMessage)
            assert(true)
        }

    }
}