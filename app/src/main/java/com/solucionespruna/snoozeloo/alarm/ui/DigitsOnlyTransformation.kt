package com.solucionespruna.snoozeloo.alarm.ui

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.core.text.isDigitsOnly

class DigitsOnlyTransformation : InputTransformation {
    override fun TextFieldBuffer.transformInput() {
        if (!asCharSequence().isDigitsOnly()) revertAllChanges()
    }
}
