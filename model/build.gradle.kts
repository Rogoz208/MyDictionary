plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 27
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "com.example.model"
}

dependencies {

    // Room
    implementation(Room.ROOM_RUNTIME)
    kapt(Room.ROOM_COMPILER)
    implementation(Room.ROOM_KTX)
    implementation(Room.GOOGLE_GSON)

    // Test
    testImplementation(TestImpl.JUNIT)
    androidTestImplementation(TestImpl.ANDROID_JUNIT)
    androidTestImplementation(TestImpl.ESPRESSO_CORE)
}