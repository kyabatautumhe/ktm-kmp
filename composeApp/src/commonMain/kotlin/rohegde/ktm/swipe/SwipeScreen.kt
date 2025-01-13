package rohegde.ktm.swipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import rohegde.ktm.model.DemoProfiles
import rohegde.ktm.model.Profile


@Composable
fun SwipeScreen() {
    MaterialTheme {
        val lastIndex = DemoProfiles.profiles.lastIndex
        val currentIndex = rememberSaveable { mutableIntStateOf(0) }

        var hint by remember {
            mutableStateOf("Try to swipe a card")
        }

        // List of Dummy profile card
        val profileList = remember { mutableListOf<Profile>() }.apply {
            addAll(DemoProfiles.profiles)
        }

        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(contentPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    profileList.reversed().forEachIndexed { _, profile ->
                        ProfileCard(
                            profile = profile,
                            onSwipe = {
//                                hint = "Swiped towards ${stringFrom(it)}"
                                currentIndex.intValue++

                                // remove swiped profile from list
                                profileList.removeFirst()

                                // Dismiss previous SnackBar
                                val currentSnackBar = snackBarHostState.currentSnackbarData
                                currentSnackBar?.dismiss()

                                // Show Snack bar
                                scope.launch {
                                    val result = snackBarHostState
                                        .showSnackbar(
                                            message = "Undo swiped card",
                                            actionLabel = "Undo",
                                            duration = SnackbarDuration.Short
                                        )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        /* Handle undo action performed */
                                        currentIndex.intValue-- // Decrement index
                                        // add swiped profile to list
                                        profileList.add(0, profile)
                                    }
                                }
                            }
                        )
                    }
                    if (currentIndex.intValue > lastIndex) {
                        Text(
                            text = "All Cards Swiped",
                            fontSize = 26.sp,
//                            fontFamily = FontFamily.appFontFamily,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.Center
                        )
                    }
                }
//                ResetButton(
//                    onClick = {
//                        currentIndex.intValue = 0
//                        // Dismiss previous SnackBar
//                        val currentSnackBar = snackBarHostState.currentSnackbarData
//                        currentSnackBar?.dismiss()
//                        profileList.clear()
//                        profileList.addAll(DummyProfile.list)
//                    },
//                    text = "Reset"
//                )

//                Hint(text = hint)
            }
        }
    }
}

@Composable
fun SwipableCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .swipeableCard(
                onSwipe = { direction ->
                    println("The card was swiped towards $direction")
                },
                enableSpringEffect = true
            )
    )
}

@Composable
fun Test() {
    Box(modifier = Modifier.fillMaxSize())
}