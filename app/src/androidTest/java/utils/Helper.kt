package utils

import com.agoda.kakao.common.assertions.BaseAssertions

object Helper {
    fun BaseAssertions.waitUntilDisplayed() {
        var counter = 0
        if(counter < 10) {
            this.isDisplayed()
            counter++
        } else {
            Thread.sleep(500)
        }
    }

    fun BaseAssertions.waitUntilVisible() {
        var counter = 0
        if(counter < 10) {
            this.isVisible()
            counter++
        } else {
            Thread.sleep(500)
        }
    }
}