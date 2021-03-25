package com.academy.android.data.repositories

import com.academy.android.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(

) {
    private val _newsList = MutableStateFlow<List<News>>(provideMokkNews())
    val newsList: StateFlow<List<News>> = _newsList

    private val likedNews = mutableSetOf<Long>()

    fun updateLiked(id: Long, isLiked: Boolean): Boolean {
        if (isLiked) likedNews.add(id)
        else likedNews.remove(id)
        return isLiked
    }

    fun getIsLikedState(id: Long): Boolean =
        id in likedNews



    private fun getOldDate(): Date {
        val c = Calendar.getInstance()
        c.set(2021, 2, 20)
        return c.time
    }

    private fun getFutureDate(): Date {
        val c = Calendar.getInstance()
        c.set(2022, 2, 20)
        return c.time
    }

    private fun provideMokkNews(): List<News> = listOf(
        News(
            id = 1,
            text = """В понедельник было не просто! Артур поведал много всего про реактивные подходы и саму библиотеку RxJava. Хоть домашки к этой лекции не будет, но скучать не придется. Ведь есть RxRiddles.""",
            link = "https://developer.android.com",
            picture = "https://multi-thread.com/wp-content/uploads/2019/05/hqdefault.jpg",
            date = getFutureDate()
        ),
        News(
            id = 2,
            text = """Привет!

Уже готовы к хакатону? Может, и команду собрали?

Отлично! У вас есть ещё 2 часа, чтобы в свободной форме переписываться с людьми в https://t.me/joinchat/TNmpVpm0HZ9jODAy и сформировать команду вашей мечты! 
""",
            link = "https://developer.android.com",
            picture = "https://pbs.twimg.com/media/EaiVBbgXQAAKSED.png",
            date = getFutureDate()
        ),
        News(
            id = 3,
            text = """3 В понедельник было не просто! Артур поведал много всего про реактивные подходы и саму библиотеку RxJava. Хоть домашки к этой лекции не будет, но скучать не придется. Ведь есть RxRiddles.""",
            link = "https://developer.android.com",
            picture = "https://multi-thread.com/wp-content/uploads/2019/05/hqdefault.jpg",
            date = getFutureDate()
        ),
        News(
            id = 4,
            text = """4 В понедельник было не просто! Артур поведал много всего про реактивные подходы и саму библиотеку RxJava. Хоть домашки к этой лекции не будет, но скучать не придется. Ведь есть RxRiddles.""",
            link = "https://developer.android.com",
            picture = "https://multi-thread.com/wp-content/uploads/2019/05/hqdefault.jpg",
            date = getFutureDate()
        ),
        News(
            id = 5,
            text = """OLD 5
В понедельник было не просто! Артур поведал много всего про реактивные подходы и саму библиотеку RxJava. Хоть домашки к этой лекции не будет, но скучать не придется. Ведь есть RxRiddles.""".trimIndent(),
            link = "https://developer.android.com",
            picture = "https://multi-thread.com/wp-content/uploads/2019/05/hqdefault.jpg",
            date = getOldDate()
        ),
        News(
            id = 6,
            text = """OLD 6
Привет!

Уже готовы к хакатону? Может, и команду собрали?

Отлично! У вас есть ещё 2 часа, чтобы в свободной форме переписываться с людьми в https://t.me/joinchat/TNmpVpm0HZ9jODAy и сформировать команду вашей мечты! 
""",
            link = "https://developer.android.com",
            picture = "https://pbs.twimg.com/media/EaiVBbgXQAAKSED.png",
            date = getOldDate()
        ),
        News(
            id = 7,
            text = """OLD 7
В понедельник было не просто! Артур поведал много всего про реактивные подходы и саму библиотеку RxJava. Хоть домашки к этой лекции не будет, но скучать не придется. Ведь есть RxRiddles.""",
            link = "https://developer.android.com",
            picture = "https://multi-thread.com/wp-content/uploads/2019/05/hqdefault.jpg",
            date = getOldDate()
        ),
        News(
            id = 8,
            text = """OLD 8
В понедельник было не просто! Артур поведал много всего про реактивные подходы и саму библиотеку RxJava. Хоть домашки к этой лекции не будет, но скучать не придется. Ведь есть RxRiddles.""",
            link = "https://developer.android.com",
            picture = "https://multi-thread.com/wp-content/uploads/2019/05/hqdefault.jpg",
            date = getOldDate()
        ),
    )
}