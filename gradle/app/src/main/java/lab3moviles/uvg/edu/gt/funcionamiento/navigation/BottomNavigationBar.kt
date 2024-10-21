package lab3moviles.uvg.edu.gt.funcionamiento.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    checkItemSelected: (Any) -> Boolean,
    onNavItemClick: (Any) -> Unit
) {
    NavigationBar {
        navigationItems.forEach { navItem ->
            val isItemSelected = checkItemSelected(navItem.destination)
            NavigationBarItem(
                selected = isItemSelected,
                label = { Text(navItem.title) },
                onClick = {
                    onNavItemClick(navItem.destination)
                },
                icon = {
                    Icon(
                        imageVector = if (isItemSelected) {
                            navItem.selectedIcon
                        } else navItem.unselectedIcon,
                        contentDescription = navItem.title
                    )
                })
        }
    }
}