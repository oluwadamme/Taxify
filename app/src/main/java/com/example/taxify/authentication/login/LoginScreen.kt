package com.example.taxify.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.taxify.uicommon.UnterHeader
import com.example.taxify.R

import com.example.taxify.style.*

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = color_white),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        UnterHeader(
            modifier = Modifier.padding(16.dp),
            subtitleText = stringResource(id = R.string.need_a_ride)
        )
        EmailInputField(modifier = Modifier.padding(top = 16.dp), viewModel)
        PasswordInputField(modifier = Modifier.padding(top = 16.dp), viewModel)
        LoginButton(
            modifier = Modifier.padding(top = 16.dp),
            handleLogin = { viewModel.handleLogin() },
        )
        SignupText(modifier = Modifier.padding(top = 32.dp), viewModel)
    }
}

@Composable
fun SignupText(modifier: Modifier, viewModel: LoginViewModel) {
    TextButton(onClick = { viewModel.goToSignup() }, modifier = modifier) {
        Text(text = buildAnnotatedString {
            append(stringResource(id = R.string.no_account))
            append(" ")
            withStyle(
                SpanStyle(
                    color = color_primary,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(stringResource(id = R.string.sign_up))
            }

            append(" ")
            append(stringResource(id = R.string.here))
        }, style = typography.subtitle2)
    }
}

@Composable
fun EmailInputField(modifier: Modifier, viewModel: LoginViewModel) {
    OutlinedTextField(
        modifier = modifier, value = viewModel.email,
        onValueChange = {
            viewModel.updateEmail(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = stringResource(id = R.string.email)) },
    )
}

@Composable
fun PasswordInputField(modifier: Modifier, viewModel: LoginViewModel) {
    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier, value = viewModel.password,
        onValueChange = {
            viewModel.updatePassword(it)
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { Text(text = stringResource(id = R.string.password)) },
        trailingIcon = {
            val image = if (showPassword)
                Icons.Filled.Visibility
            else Icons.Filled.Visibility
            val description =
                if (showPassword) stringResource(id = R.string.hide_password) else stringResource(
                    id = R.string.show_password
                )
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(imageVector = image, contentDescription = description)
            }
        }
    )
}

@Composable
fun LoginButton(
    modifier: Modifier,
    handleLogin: () -> Unit
) {
    Button(modifier = modifier, colors = ButtonDefaults.buttonColors(
        backgroundColor = color_primary, contentColor = color_white
    ), onClick = { handleLogin() }) {
        Text(text = stringResource(id = R.string.string_continue), style = typography.button)
    }
}





