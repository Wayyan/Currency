plugins {
  id("java-library")
  id("kotlin")
  kotlin("kapt")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation(project(":domain"))

  testImplementation(CommonLibs.junit)
  mockito()

  implementation(Kotlin.stdblib_jdk)
  api(KotlinCoroutine.core)

  //di koin
  koinCore()
}