buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath (libs.gradle)
        classpath (libs.kotlin.gradle.plugin)
        classpath (libs.hilt.android.gradle.plugin)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
