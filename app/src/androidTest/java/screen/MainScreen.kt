package screen

import co.`fun`.testgiphy.MainActivity
import co.`fun`.testgiphy.R
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspresso.screens.KScreen

object MainScreen: KScreen<MainScreen>() {
    override val layoutId: Int?
        get() = R.layout.activity_main
    override val viewClass: Class<*>?
        get() = MainActivity::class.java

    val searchButton get() = KButton { withId(R.id.menu_trending_search) }
}