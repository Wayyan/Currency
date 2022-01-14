plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdk = BuildConfig.compileSdk

  defaultConfig {
    minSdk = BuildConfig.minSdk
    targetSdk = BuildConfig.targetSdk

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")

    multiDexEnabled = true
  }

  buildTypes {
    getByName("debug") {
      isMinifyEnabled = false
    }

    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }

  externalNativeBuild {
    cmake {
      this.path("CMakeLists.txt")
    }
  }
}

dependencies {
  implementation(project(":data:common"))
  implementation(project(":domain"))

  coreLibraryDesugaring(CommonLibs.desugar_lib)

  implementation(Kotlin.stdblib_jdk)
  implementation(AndroidXCore.core_ktx)

  implementation(OkHttp.client)
  implementation(OkHttp.logger)
  testImplementation(OkHttp.mock_web_server)

  implementation(Retrofit.core)
  implementation(Retrofit.moshi_converter)
  implementation(AndroidXSecurity.crypto)
  moshi()

  //di
  //koin
  koinAndroid()

  //Testing
  testImplementation(CommonLibs.junit)
  mockito()
  mockitoAndroid()
  androidXTest()

  //timber for logging
  implementation(CommonLibs.timber)
}