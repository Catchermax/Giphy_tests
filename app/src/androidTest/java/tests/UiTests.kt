package tests

import androidx.test.core.app.ActivityScenario
import co.`fun`.testgiphy.MainActivity
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import scenarios.SearchGifsScenario
import screen.SearchScreen
import utils.Helper.waitUntilDisplayed
import utils.Helper.waitUntilVisible

//todo сделать параметризированным, вынести steps в scenario, добавить Matcher для проверки теста, подключить отчеты
@RunWith(Parameterized::class)
class UiTests(val textForSearch: String) : TestCase(kaspressoBuilder = Kaspresso.Builder.simple().apply {
    beforeEachTest { ActivityScenario.launch(MainActivity::class.java) }
}) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : Collection<Array<Any>> {
            return listOf(
                arrayOf("cat"),
                arrayOf("CAT")
            )
        }
    }

    @Test
    fun findGifsTest() {
        before {
            testLogger.i("test has been started")
        }.after {
            testLogger.i("test has been complete")
        }.run {
            scenario(SearchGifsScenario(textForSearch))
            step("Check results") {
                SearchScreen {
                    resultSearchRecycler.hasSize(10)

                    resultSearchRecycler {
                        children<SearchScreen.CardGif> {
                            cardTitle.waitUntilDisplayed()
//                            cardTitle.containsText("cat") todo раскоментировать если ожидаемый результат "Все найденные результаты должны содержать в заголовке строку поиска"
                            gifImage.waitUntilDisplayed()
                        }
                    }
                }
            }
        }
    }

    @Test
    fun searchForNonExistentGifTest() {
        before {
            testLogger.i("test has been started")
        }.after {
            testLogger.i("test has been complete")
        }.run {
            scenario(SearchGifsScenario("asdfsdfadf"))
            step("Check results") {
                SearchScreen {
                    resultSearchRecycler.hasSize(1)
                    resultSearchRecycler.firstChild<SearchScreen.CardGif> {
                        emptyListTextView.waitUntilVisible()
                        emptyListTextView.hasText("Empty list")
                    }
                }
            }
        }
    }
}
