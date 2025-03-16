package com.inconceptlabs.designsystem.components.bottomnavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.theme.AppTheme

@Preview(showBackground = true)
@Composable
private fun ComponentPreview() {
    val items = listOf(
        BottomNavigationItem(
            icon = painterResource(id = R.drawable.ic_home),
            text = "Home",
            isSelected = true,
        ),
        BottomNavigationItem(
            icon = painterResource(id = R.drawable.ic_search),
            text = "Search"
        ),
        BottomNavigationItem(
            icon = painterResource(id = R.drawable.ic_bookmark),
            text = "Bookmarks"
        )
    )

    AppTheme {
        BottomNavigation(
            items = items,
            isLabeled = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}