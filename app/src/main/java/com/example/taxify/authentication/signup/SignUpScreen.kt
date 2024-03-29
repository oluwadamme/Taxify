package com.example.taxify.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.taxify.R
import com.example.taxify.style.color_primary
import com.example.taxify.style.color_white
import com.example.taxify.style.typography
import com.example.taxify.uicommon.UnterHeader

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color_white),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Icon(
                modifier = Modifier.clickable { viewModel.handleBackPress() },
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(id = R.string.close_icon),
            )
        }

        UnterHeader(
            modifier = Modifier.padding(top = 64.dp),
            subtitleText = stringResource(id = R.string.sign_up_for_free)
        )

        UsernameInputField(
            modifier = Modifier.padding(top = 16.dp),
            viewModel = viewModel
        )

        EmailInputField(
            modifier = Modifier.padding(top = 16.dp),
            viewModel = viewModel
        )

        PasswordInputField(
            modifier = Modifier.padding(top = 16.dp),
            viewModel = viewModel
        )

        SignUpContinueButton(
            modifier = Modifier.padding(top = 32.dp),
            viewModel = viewModel
        )
    }
}

@Composable
fun SignUpContinueButton(
    modifier: Modifier,
    viewModel: SignUpViewModel
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color_primary,
            contentColor = color_white
        ),
        onClick = { viewModel.handleSignUp() },
    ) {
        Text(
            text = stringResource(id = R.string.string_continue),
            style = typography.button
        )
    }
}

@Composable
fun UsernameInputField(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel
) {
    OutlinedTextField(
        modifier = modifier,
        value = viewModel.name,
        onValueChange = {
            viewModel.updateName(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = stringResource(id = R.string.user_name)) }
    )
}

@Composable
fun EmailInputField(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel
) {
    OutlinedTextField(
        modifier = modifier,
        value = viewModel.email,
        onValueChange = {
            viewModel.updateEmail(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = stringResource(id = R.string.email)) }
    )
}

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel
) {

    var showPassword by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        value = viewModel.password,
        onValueChange = {
            viewModel.updatePassword(it)
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { Text(text = stringResource(id = R.string.password)) },
        trailingIcon = {
            val image = if (showPassword)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (showPassword) stringResource(id = R.string.hide_password) else stringResource(id = R.string.show_password)
            IconButton(onClick = { showPassword = !showPassword}){
                Icon(imageVector  = image, description)
            }
        }
    )
}