package com.yardcampaign.data.model

enum class QuestionType(i: Int) {
    MULTI_SELECT(1),
    SINGLE_SELECT(2),
    TORQUE(3),
    NUMERIC(4),
    TEXT_INPUT(5),
    DATE(6),
    IMAGE(7),
    GOOD_NO_GOOD(8);

    val intValue = i
}