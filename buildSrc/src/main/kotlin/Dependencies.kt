object Versions {
    const val VIEW_BINDING = "1.5.6"
    const val RETROFIT = "2.9.0"
    const val OK_HTTP3 = "3.12.1"
    const val KOIN = "3.2.0-beta-1"
    const val PICASSO = "2.8"
    const val ROOM = "2.4.2"
    const val COROUTINES = "1.6.1"
    const val LYFECYCLE = "2.4.1"
    const val KOTLIN = "1.7.0"
    const val GOOGLE_GSON = "2.8.9"

    // Design
    const val APPCOMPAT = "1.4.1"
    const val MATERIAL = "1.5.0"
    const val FRAGMENT_KTX = "1.4.1"
    const val CONSTRAINT_LAYOUT = "2.1.3"
    const val SWIPE_REFRESH_LAYOUT = "1.1.0"

    //Test
    const val JUNIT = "4.13.2"
    const val ANDROID_JUNIT = "1.1.3"
    const val ESPRESSO = "3.4.0"
}

object Modules {
    const val MODEL = ":model"
    const val RETROFIT = ":retrofit"
}

object ViewBinding {
    const val VIEW_BINDING =
        "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.VIEW_BINDING}"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val ADAPTER_RX_JAVA = "com.squareup.retrofit2:adapter-rxjava3:${Versions.RETROFIT}"
}

object OkHttp3 {
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP3}"
}

object Koin {
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
}

object Picasso {
    const val PICASSO = "com.squareup.picasso:picasso:${Versions.PICASSO}"
}

object Room {
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val GOOGLE_GSON = "com.google.code.gson:gson:${Versions.GOOGLE_GSON}"
}

object Kotlin {
    const val KOTLIN_CORE = "androidx.core:core-ktx:${Versions.KOTLIN}"
}

object Coroutines {
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

}

object Lifecycle {
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LYFECYCLE}"
    const val LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LYFECYCLE}"
}

object Design {
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT}"
}

object TestImpl {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ANDROID_JUNIT = "\"androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
}