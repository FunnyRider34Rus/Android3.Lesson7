package com.elpablo.android3lesson7.viewmodel

import com.elpablo.android3lesson7.model.APODDTO

sealed class APODState {
    data class Success (val serverResponseData: APODDTO) : APODState()
    data class Error (val error: Throwable) : APODState()
    data class Loading (val progress: Int?) : APODState()
}