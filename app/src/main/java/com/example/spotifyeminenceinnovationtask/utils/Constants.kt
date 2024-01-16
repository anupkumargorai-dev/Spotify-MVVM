package com.example.spotifyeminenceinnovationtask.utils

class Constants {

    annotation class ApiEndPoints {
        companion object{
            const val Albums = "albums"
        }
    }

    annotation class ApiHeaders {
        companion object {
            const val ApiKey = "apikey"
        }
    }

    annotation class AppConstants {
        companion object{
            const val Static_Album_id = "1xXv2wox60JAkIKUl8QwWp"
            const val Album_table_name = "album"
            const val AppDataBase = "app_database"
        }
    }
}