// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
}

buildscript {
    dependencies {
        classpath("com.badlogicgames.gdx:gdx-backend-android:1.9.12")
    }
}