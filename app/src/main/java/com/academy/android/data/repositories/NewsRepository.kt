package com.academy.android.data.repositories

import com.academy.android.model.News
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

interface NewsRepositorySource{
    val newsList: StateFlow<List<News>>
    fun updateLiked(id: Long, isLiked: Boolean): Boolean
    fun getIsLikedState(id: Long): Boolean
    fun getLikesCountForId(chatId: Long): Flow<Int>
}


class NewsRepository @Inject constructor(

):NewsRepositorySource {
    private val _newsList = MutableStateFlow<List<News>>(provideMokkNews())
   override val newsList: StateFlow<List<News>> = _newsList

    private val likedNews = mutableSetOf<Long>()

    private val likesCountState = MutableStateFlow<Map<Long, Int>>(provideMokkLikes())


    override fun updateLiked(id: Long, isLiked: Boolean): Boolean {
        if (isLiked) {
            likedNews.add(id)
            increaseLikesCountForId(id)
        } else {
            likedNews.remove(id)
            decreaseLikesCountForId(id)
        }
        return isLiked
    }

    override  fun getIsLikedState(id: Long): Boolean =
        id in likedNews

    override fun getLikesCountForId(chatId: Long): Flow<Int> =
        likesCountState.filter { it.containsKey(chatId) }.map { it[chatId] ?: 0 }

    private fun increaseLikesCountForId(id: Long) {
        val likesCount = likesCountState.value.toMutableMap()
        val currentValue = likesCount[id] ?: 0
        likesCount[id] = currentValue + 1
        likesCountState.value = likesCount
    }

    private fun decreaseLikesCountForId(id: Long) {
        val likesCount = likesCountState.value.toMutableMap()
        val currentValue = likesCount[id] ?: 0
        likesCount[id] = if (currentValue > 0) currentValue - 1 else currentValue
        likesCountState.value = likesCount
    }

    private fun getOldDate(): Date = Date()

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

    private fun provideMokkLikes(): Map<Long, Int> =
        mutableMapOf<Long, Int>().apply {
            (1L..8L).forEach { i ->
                put(i, (10..50).random())
            }
        }
}