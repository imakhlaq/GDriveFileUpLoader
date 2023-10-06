package com.drive.model

import java.time.LocalDateTime

data class StoredFiles(val fileName: String, val url: String, val type: String, val timestamp: LocalDateTime)
