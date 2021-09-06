package com.velmurugan.mypersonalassitant.data

sealed class VoiceAction {
    data class Note(val text: String)
    data class Remainder(val dateTime: String)
}
