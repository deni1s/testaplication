package com.example.data

import com.example.data.models.RestClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class RetrofitTest {

    @Test
    fun testRetrofit() {
        testJsonResponse()
        testRssResponse()
    }

    private fun testJsonResponse() = runBlocking {
        val restClient = RestClient()

        val newsResponse =
            restClient.getClient(getStringFromFile("/successnewsresponse.json"), true)!!.loadJsonNews(
                "newsapi.org"
            ).await();
        Assert.assertEquals(true, newsResponse.isSuccessful())
    }

    private fun testRssResponse() = runBlocking {
        val restClient = RestClient()

        val newsResponse =
            restClient.getClient(getStringFromFile("/successnewsrssresponse.xml"), false)!!.loadJsonNews(
                "newsapi.org"
            ).await();
        Assert.assertEquals(true, newsResponse.isSuccessful())
    }

    private fun getStringFromFile(filePath : String): String {
        return RetrofitTest::class.java.getResource(filePath)!!.readText()
    }
}