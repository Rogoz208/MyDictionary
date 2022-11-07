plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.mydictionary"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    namespace = "com.example.mydictionary"
}

dependencies {
    // Modules
    implementation(project(Modules.RETROFIT))
    implementation(project(Modules.MODEL))

    // View binding
    implementation(ViewBinding.VIEW_BINDING)

    // Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.CONVERTER_GSON)
    implementation(Retrofit.ADAPTER_RX_JAVA)
    implementation(OkHttp3.LOGGING_INTERCEPTOR)

    // Koin
    implementation(Koin.KOIN_CORE)
    implementation(Koin.KOIN_ANDROID)

    // Picasso
    implementation(Picasso.PICASSO)

    // Room
    implementation(Room.ROOM_RUNTIME)
    kapt(Room.ROOM_COMPILER)
    implementation(Room.ROOM_KTX)

    // Coroutines
    implementation(Coroutines.COROUTINES_CORE)
    implementation(Coroutines.COROUTINES_ANDROID)
    implementation(Lifecycle.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lifecycle.LIFECYCLE_RUNTIME_KTX)

    // Android
    implementation(Kotlin.KOTLIN_CORE)
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.FRAGMENT_KTX)
    implementation(Design.CONSTRAINT_LAYOUT)
    implementation(Design.SWIPE_REFRESH_LAYOUT)
    implementation(Design.SPLASH_SCREEN)

    // Test
    testImplementation(TestImpl.JUNIT)
    androidTestImplementation(TestImpl.ANDROID_JUNIT)
    androidTestImplementation(TestImpl.ESPRESSO_CORE)
}