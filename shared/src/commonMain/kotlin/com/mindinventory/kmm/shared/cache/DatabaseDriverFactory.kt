package com.mindinventory.kmm.shared.cache

import com.squareup.sqldelight.db.SqlDriver

// this expects native implementations for SqlDriver
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}