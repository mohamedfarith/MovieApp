plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.farith.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.farith.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //fresco
    implementation ("com.facebook.fresco:fresco:3.1.3")
    val appcompatVersion = "1.6.1"
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    // Dagger
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.48")
    //recycler
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("com.squareup.retrofit2:retrofit:2.6.3")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.3")
    //testing
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("com.android.support.test:runner:1.0.2")
    androidTestImplementation ("com.android.support.test.espresso:espresso-core:3.0.2")
}
