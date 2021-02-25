

package screen

import android.view.View
import co.`fun`.testgiphy.R
import co.`fun`.testgiphy.search.FragmentSearch
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspresso.screens.KScreen
import org.hamcrest.Matcher

object SearchScreen: KScreen<SearchScreen>() {
    override val layoutId: Int?
        get() = R.layout.fragment_search
    override val viewClass: Class<*>?
        get() = FragmentSearch::class.java

    val searchField get() = KEditText { withId(R.id.etSearchToolbar) }

    val resultSearchRecycler get() = KRecyclerView(
        {
            withId(R.id.rvFragmentSearch)
            withParent { withId(R.id.rootFragmentSearch) }
        },
        itemTypeBuilder = {
            itemType(::CardGif)
        })

    class CardGif(parent: Matcher<View>) : KRecyclerItem<CardGif>(parent) {
        val cardTitle = KTextView(parent) { withId(R.id.tvGiphyCardTitle) }
        val gifImage = KImageView(parent) { withId(R.id.ivGiphyCard) }
        val emptyListTextView = KTextView(parent) {withId(R.id.tvEmptyListVH)}
    }
}