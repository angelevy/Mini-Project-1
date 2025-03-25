package com.angellevyne0045.assesmentmobpro1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.angellevyne0045.assesmentmobpro1.R
import com.angellevyne0045.assesmentmobpro1.model.Gender
import com.angellevyne0045.assesmentmobpro1.ui.theme.AssesmentMobpro1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val data = listOf(
        Gender("Man", R.drawable.man),
        Gender("Woman", R.drawable.woman),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(data[0], Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(gender: Gender, modifier: Modifier = Modifier) {
    var usia by remember { mutableStateOf("") }
    var berat by remember { mutableStateOf("") }
    var tinggi by remember { mutableStateOf("") }

    val radioOptions = listOf(
        Gender("Pria", R.drawable.man),
        Gender("Wanita", R.drawable.woman),
    )
    var pilihGender by remember { mutableStateOf(gender) }
    var pilihlevel by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kalori),
            contentDescription = stringResource(id = R.string.kalori),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = stringResource(id = R.string.bmr_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        LevelAktifitas(
            selectedText = pilihlevel,
            onTextSelected = { pilihlevel = it },
        )

        OutlinedTextField(
            value = usia,
            onValueChange = { usia = it },
            label = { Text(text = stringResource(R.string.usia)) },
            trailingIcon = { Text(text = "Tahun", modifier = Modifier.padding(16.dp)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = berat,
            onValueChange = { berat = it },
            label = { Text(text = stringResource(R.string.berat_badan)) },
            trailingIcon = { Text(text = "Kg") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tinggi,
            onValueChange = { tinggi = it },
            label = { Text(text = stringResource(R.string.tinggi_badan)) },
            trailingIcon = { Text(text = "Cm") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { genderOption ->
                GenderOption(
                    label = genderOption.nama,
                    imageResId = genderOption.imageResId,
                    isSelected = pilihGender == genderOption,
                    modifier = Modifier
                        .selectable(
                            selected = pilihGender == genderOption,
                            onClick = { pilihGender = genderOption },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }

        Button(
            onClick = {},
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.hitung))
        }
    }
}

@Composable
fun GenderOption(label: String, imageResId: Int, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = label,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelAktifitas(selectedText: String, onTextSelected: (String) -> Unit) {
    val list = listOf("Tidak Aktif", "Aktivitas Ringan", "Aktivitas Sedang", "Aktivitas Berat", "Aktifitas Sangat Berat")
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                value = selectedText,
                label = { Text(text = stringResource(R.string.level_aktifitas)) },
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                list.forEachIndexed { _, text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            onTextSelected(text)
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    AssesmentMobpro1Theme {
        MainScreen()
    }
}
