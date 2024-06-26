import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun HandleBackPress(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    onBack: () -> Unit
) {
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }

    DisposableEffect(key1 = onBackPressedDispatcher) {
        backCallback.isEnabled = true
        onBackPressedDispatcher.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}
