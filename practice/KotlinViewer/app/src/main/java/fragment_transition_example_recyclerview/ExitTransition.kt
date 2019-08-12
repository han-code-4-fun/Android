package fragment_transition_example_recyclerview

import android.annotation.TargetApi
import android.graphics.Rect
import android.os.Build
import android.transition.Explode
import android.transition.Transition
import android.view.View


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
private fun getListFragmentExitTransition(itemView: View): Transition {
    val epicCenterRect = Rect()
    //itemView is the full-width inbox item's view
    itemView.getGlobalVisibleRect(epicCenterRect)
    // Set Epic center to a imaginary horizontal full width line under the clicked item, so the explosion happens vertically away from it
    epicCenterRect.top = epicCenterRect.bottom
    val exitTransition = Explode()
    exitTransition.epicenterCallback = object : Transition.EpicenterCallback() {
        override fun onGetEpicenter(transition: Transition): Rect {
            return epicCenterRect
        }
    }
}