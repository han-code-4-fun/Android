package examlple.com.kotlinviewer

import androidx.core.content.ContextCompat.startActivity



myViewModel.navigateToDetails.observe(this, Observer {
    it.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled
        //do somtthign
    }
})