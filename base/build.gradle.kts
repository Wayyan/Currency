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

    buildFeatures {
      viewBinding = true
    }

    multiDexEnabled = true
  }

  buildTypes {
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
}

dependencies {
  api(project(":domain"))
  api(project(":data:common"))
  api(project(":data:cache"))
  api(project(":data:network"))

  coreLibraryDesugaring(CommonLibs.desugar_lib)

  implementation(Kotlin.stdblib_jdk)

  //AndroidX
  implementation(AndroidXAppCompat.app_compat)
  implementation(AndroidXCore.core_ktx)
  implementation(AndroidXRecyclerView.recycler_view)
  androidxActivity()
  androidxFragment()
  androidXArch()

  //Material
  implementation(Material.material)

  //Timber
  implementation(CommonLibs.timber)

  //Testing
  testImplementation(CommonLibs.junit)
  mockito()
  mockitoAndroid()
  androidXTest()

  //di
  koinAndroid()
}