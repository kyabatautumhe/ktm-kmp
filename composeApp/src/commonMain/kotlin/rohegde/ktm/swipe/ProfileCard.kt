package rohegde.ktm.swipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.ui.tooling.preview.Preview
import rohegde.ktm.model.Profile


@Composable
fun ProfileCard(
    profile: Profile,
    onSwipe: (SwipeDirection) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
//        colors = CardDefaults.cardColors(
//            containerColor = Color.LightGray,
//        ),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .swipeableCard(
                onSwipe = onSwipe,
                enableSpringEffect = true
            ),
//        elevation = CardDefaults.cardElevation(8.dp)
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = profile.name,
                modifier = Modifier.padding(16.dp),
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                fontSize = 200.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewProfileCard() {
    ProfileCard(
        profile = Profile("John Doe"),
        onSwipe = {}
    )
}