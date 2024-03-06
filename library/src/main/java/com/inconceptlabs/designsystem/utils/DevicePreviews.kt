package com.inconceptlabs.designsystem.utils

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Phone",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PHONE
)
@Preview(
    name = "Tablet",
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=600dp,height=900dp,dpi=240"
)
annotation class DevicePreviews