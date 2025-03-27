package com.angellevyne0045.assesmentmobpro1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.angellevyne0045.assesmentmobpro1.R
import com.angellevyne0045.assesmentmobpro1.model.Gender
import com.angellevyne0045.assesmentmobpro1.navigation.Screen
import com.angellevyne0045.assesmentmobpro1.ui.theme.AssesmentMobpro1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
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
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    } ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(data[0], Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(gender: Gender, modifier: Modifier = Modifier) {
    var usia by rememberSaveable { mutableStateOf("") }
    var usiaError by rememberSaveable { mutableStateOf(false) }

    var berat by rememberSaveable { mutableStateOf("") }
    var beratError by rememberSaveable { mutableStateOf(false) }

    var tinggi by rememberSaveable { mutableStateOf("") }
    var tinggiError by rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf(
        Gender("Pria", R.drawable.man),
        Gender("Wanita", R.drawable.woman),
    )
    var pilihGender by remember { mutableStateOf(gender) }

    var pilihlevel by rememberSaveable { mutableStateOf("") }
    var pilihLevelError by rememberSaveable { mutableStateOf(false) }


    var bmr by rememberSaveable { mutableFloatStateOf(0f) }

    val context = LocalContext.current



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
            trailingIcon = { IconPicker(usiaError, "Tahun   ") },
            supportingText = { ErrorHint(usiaError) },
            isError = usiaError,
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
            trailingIcon = { IconPicker(beratError, "Kg") },
            supportingText = { ErrorHint(beratError) },
            isError = beratError,
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
            trailingIcon = { IconPicker(tinggiError, "Cm") },
            supportingText = { ErrorHint(tinggiError) },
            isError = tinggiError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
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
                    imageResId = genderOption.imagesResId,
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
            onClick = {
                pilihLevelError = (pilihlevel == "" || pilihlevel == "0")
                usiaError = (usia == "" || usia == "0")
                beratError = (berat == "" || berat == "0")
                tinggiError = (tinggi == "" || tinggi == "0")
                if (usiaError || beratError || tinggiError) return@Button

                bmr = hitungKalori(
                    usia = usia.toFloat(),
                    berat = berat.toFloat(),
                    tinggi = tinggi.toFloat(),
                    isMale = gender == radioOptions[0],
                    aktivitas = pilihlevel
                )
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.hitung))
        }
        if (bmr != 0f) {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(R.string.bmr, bmr),
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(
                            R.string.bagikan_template,
                            pilihlevel, usia, berat, tinggi, pilihGender, bmr
                        )
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.bagikan))
            }
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

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun hitungKalori(usia: Float, berat: Float, tinggi: Float, isMale: Boolean, aktivitas: String): Float {
    val bmr = if (isMale) {
        66 + (13.7f * berat) + (5f * tinggi) - (6.8f * usia)
    } else {
        655 + (9.6f * berat) + (1.8f * tinggi) - (4.7f * usia)
    }

    val faktorAktivitas = when (aktivitas) {
        "Tidak Aktif" -> 1.2f
        "Aktivitas Ringan" -> 1.375f
        "Aktivitas Sedang" -> 1.55f
        "Aktivitas Berat" -> 1.72f
        "Aktivitas Sangat Berat" -> 1.9f
        else -> 1.2f
    }
    return bmr * faktorAktivitas
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

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT,message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    AssesmentMobpro1Theme {
        MainScreen(rememberNavController())
    }
}
