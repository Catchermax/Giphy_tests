package scenarios

import android.view.KeyEvent
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import screen.MainScreen
import screen.SearchScreen

class SearchGifsScenario(private val text: String) : Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        testLogger.i("The given text is \"$text\"")

        step("Open search") {
            MainScreen {
                searchButton.isClickable()
                searchButton.click()
            }
        }

        step("Find gifs by text: \"$text\"") {
            SearchScreen {
                searchField {
                    isVisible()
                    typeText("$text")
                    hasText("$text")
                    pressImeAction()
                }
                pressKey(KeyEvent.KEYCODE_ENTER)
            }
        }
    }
}