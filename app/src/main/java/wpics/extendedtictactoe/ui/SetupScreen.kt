package wpics.extendedtictactoe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuAnchorType.Companion.PrimaryNotEditable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import wpics.extendedtictactoe.ui.theme.*

/**
 * This composable provides code for the game setup screen.
 *
 * @param modifier A modifier.
 * @param setupViewModel A view model that accompanies this setup view.
 * @param navController A navigation controller.
 */
@Composable
fun SetupScreen(
    modifier: Modifier = Modifier,
    setupViewModel: SetupViewModel = viewModel(),
    navController: NavHostController
) {
    // Displayed as column
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(WPI_Gray),
        verticalArrangement = Arrangement.Center
    ) {
        // Header message, set up form and buttons
        SetupHeaderMsg(modifier)
        SetupForm(modifier, setupViewModel)
        Row(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SubmitButton(onClick = {
                if (setupViewModel.validateSubmitForm()) {
                    // Save the game setup options
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "numRows",
                        setupViewModel.numRowsInput.toInt()
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "numCols",
                        setupViewModel.numColsInput.toInt()
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "numToWin",
                        setupViewModel.numToWinInput.toInt()
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "numPlayers",
                        setupViewModel.numPlayersInput.toInt()
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "memCutOff",
                        MEM_CUTOFF
                    )

                    navController.navigate(
                        route = MainScreen.Game.name
                    )
                }
            }, modifier)
            CancelButton(onClick = {
                navController.navigate(
                    route = MainScreen.Start.name
                )
            }, modifier)
        }
    }
}

/**
 * This composable provides code for creating a header message.
 *
 * @param modifier A modifier.
 */
@Composable
fun SetupHeaderMsg(modifier: Modifier = Modifier) {
    Text(
        text = "Game Setup",
        fontSize = 26.sp,
        color = Purple,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        lineHeight = 26.sp,
        modifier = modifier.padding(10.dp)
    )
}

/**
 * This composable provides code for creating a setup form
 *
 * @param modifier A modifier.
 * @param setupViewModel A view model that accompanies this setup view.
 */
@Composable
fun SetupForm(
    modifier: Modifier = Modifier,
    setupViewModel: SetupViewModel
) {
    Column(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumberField(
            labelText = "Number of Rows?",
            textInput = setupViewModel.numRowsInput,
            onValueChange = { setupViewModel.numRowsInput = it },
            errorMsg = setupViewModel.hasErrorNumRowsInput,
            modifier = modifier.padding(vertical = 5.dp)
        )
        NumberField(
            labelText = "Number of Columns?",
            textInput = setupViewModel.numColsInput,
            onValueChange = { setupViewModel.numColsInput = it },
            errorMsg = setupViewModel.hasErrorNumColsInput,
            modifier = modifier.padding(vertical = 5.dp)
        )
        NumberField(
            labelText = "Number to Win?",
            textInput = setupViewModel.numToWinInput,
            onValueChange = { setupViewModel.numToWinInput = it },
            errorMsg = setupViewModel.hasErrorNumToWinInput,
            modifier = modifier.padding(vertical = 5.dp)
        )
        NumPlayersField(
            modifier = modifier.padding(vertical = 5.dp),
            setupViewModel = setupViewModel,
            errorMsg = setupViewModel.hasErrorNumPlayersInput
        )
    }
}

/**
 * This composable provides code for entering a number field.
 *
 * @param labelText A label.
 * @param textInput An input box.
 * @param onValueChange A callback function for when the value changes.
 * @param modifier A modifier.
 */
@Composable
fun NumberField(
    labelText: String,
    textInput: String,
    onValueChange: (String) -> Unit,
    errorMsg: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textInput,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(labelText) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier,
        isError = !errorMsg.isEmpty()
    )
    if (!errorMsg.isEmpty()) {
        Text(
            text = errorMsg,
            color = WPI_Red,
            textAlign = TextAlign.Left,
            modifier = modifier
        )
    }
}

/**
 * This composable provides code for a dropdown field.
 *
 * @param modifier A modifier.
 * @param setupViewModel A view model that accompanies this setup view.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumPlayersField(
    modifier: Modifier = Modifier,
    setupViewModel: SetupViewModel,
    errorMsg: String
) {
    // Number of Players Menu Item
    val numPlayersData = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10")

    // A drop-down menu that displays the number of players allowed
    ExposedDropdownMenuBox(
        expanded = setupViewModel.expanded,
        onExpandedChange = { setupViewModel.expanded = it },
    ) {
        TextField(
            value = setupViewModel.numPlayersInput,
            onValueChange = {},
            readOnly = true,
            label = { Text("Number of Players") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = setupViewModel.expanded)
            },
            modifier = modifier.menuAnchor(PrimaryNotEditable),
            isError = !errorMsg.isEmpty()
        )
        ExposedDropdownMenu(
            expanded = setupViewModel.expanded,
            onDismissRequest = { setupViewModel.expanded = false },
        ) {
            numPlayersData.forEach { num ->
                DropdownMenuItem(
                    text = { Text(num) },
                    onClick = {
                        setupViewModel.expanded = false
                        setupViewModel.numPlayersInput = num
                    }
                )
            }
        }
    }
    if (!errorMsg.isEmpty()) {
        Text(
            text = errorMsg,
            color = WPI_Red,
            textAlign = TextAlign.Left,
            modifier = modifier
        )
    }
}

/**
 * This composable is a [Button] for submitting the game setup data.
 *
 * @param onClick A callback function for when this button is clicked.
 * @param modifier A modifier.
 */
@Composable
fun SubmitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Green),
        modifier = modifier
    ) {
        Text("Submit")
    }
}

/**
 * This composable is a [Button] for resetting our app to its initial state.
 *
 * @param onClick A callback function for when this button is clicked.
 * @param modifier A modifier.
 */
@Composable
fun CancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Red),
        modifier = modifier
    ) {
        Text("Cancel")
    }
}

/**
 * The preview screen
 */
@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    ExtendedTicTacToeTheme(dynamicColor = false) {
        SetupScreen(navController = rememberNavController())
    }
}