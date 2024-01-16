plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.spotifyeminenceinnovationtask"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.spotifyeminenceinnovationtask"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //BASE_URL
        buildConfigField("String","BASE_URL","\"https://api.apilayer.com/spotify/\"")

        //API KEY
        buildConfigField("String","API_KEY","\"LO4EflIJk23t44jnl5OqhD9L65szi8ZL\"")


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    val lifecycle_version = "2.7.0"
    val RETROFIT_VERSION = "2.9.0"
    val OK_HTTP_VERSION = "4.9.0"
    val room_version = "2.6.1"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.fragment:fragment-ktx:1.5.6")


    //viewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    //LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Retrofit library dependencies
    implementation("com.squareup.retrofit2:retrofit:$RETROFIT_VERSION")
    implementation("com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION")
    implementation("com.squareup.okhttp3:okhttp:$OK_HTTP_VERSION")
    implementation("com.squareup.okhttp3:logging-interceptor:$OK_HTTP_VERSION")
    implementation("com.squareup.retrofit2:converter-scalars:$RETROFIT_VERSION")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$RETROFIT_VERSION")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")


    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.13.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.2")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //Room Database
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")



}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}