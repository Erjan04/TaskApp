plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
}
apply plugin: 'kotlin-android'
apply plugin: 'androidx.navigation.safeargs.kotlin'
android {
    compileSdk 32

    defaultConfig {
        applicationId "com.example.taskappkotlin"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    buildFeatures {
        viewBinding true
    }
}

dependencies {

    //Module
    implementation project(":domain")
    implementation project(":data")
    implementation project(":core_ui")

    // Core
    implementation 'androidx.core:core-ktx:1.7.0'

    //Navigation Component
    implementation 'androidx.navigation:navigation-fragment-ktx:2.4.2'
    implementation 'androidx.navigation:navigation-ui-ktx:2.4.2'

    //AppCompat
    implementation 'androidx.appcompat:appcompat:1.4.1'

    //MaterialDesign
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'

    //Test
    testImplementation 'junit:junit:'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    //Ktx
    implementation "androidx.core:core-ktx:1.7.0"
    implementation "androidx.fragment:fragment-ktx:1.4.1"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0-beta01"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"

    // Dagger
    implementation 'com.google.dagger:dagger-android:2.40.1'
    kapt 'com.google.dagger:dagger-compiler:2.40.1'
    implementation 'javax.inject:javax.inject:1'

    //Room
    def room_version = "2.4.2"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    kapt "androidx.room:room-compiler:2.4.2"

    //Coroutine
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
}