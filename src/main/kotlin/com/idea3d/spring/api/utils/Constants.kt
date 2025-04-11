package com.idea3d.spring.api.utils

class Constants {

    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION

        //Base API endpoint para Tasks
        const val URL_BASE_TASK = "$URL_BASE/tasks"

        //Base API endpoint para Makes
        const val URL_BASE_MAKE = "$URL_BASE/makes"

    }
}