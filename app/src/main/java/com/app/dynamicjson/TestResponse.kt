package com.app.dynamicjson


class TestResponse {




    data class Report(
        val data: Data
    )

    data class Data(
        val results: Result
    )

    data class Result(
        val Max: Long,
        val Min: Long,
        val Slope: Long
    )
}