buildscript {
    repositories {
        google()
        mavenCentral()
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
subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
