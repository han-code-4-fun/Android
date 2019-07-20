package examlple.com.kotlinviewer

import android.content.SharedPreferences
import java.util.*

class LiveSharedPreferences constructor(private val preferences: SharedPreferences) {

    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener
    private val updates: Observable<String>

    init {
        updates = Observable.create(ObservableOnSubscribe<String> { emitter ->
            listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key -> emitter.onNext(key) }

            emitter.setCancellable { preferences.unregisterOnSharedPreferenceChangeListener(listener) }

            preferences.registerOnSharedPreferenceChangeListener(listener)
        }).share()
    }


}