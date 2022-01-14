plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
  id("koin")
}

android {
  compileSdk = BuildConfig.compileSdk

  defaultConfig {
    applicationId = "com.wayyan.currency"
    minSdk = BuildConfig.minSdk
    targetSdk = BuildConfig.targetSdk
    versionCode = BuildConfig.versionCode
    versionName = BuildConfig.versionName

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    buildFeatures {
      viewBinding = true
    }

    multiDexEnabled = true

    vectorDrawables.useSupportLibrary = true
  }

  buildTypes {
    getByName("debug") {
      isMinifyEnabled = false
      isDebuggable = true
      signingConfig = signingConfigs.getByName("debug")
      versionNameSuffix = "-debug"
      applicationIdSuffix = ".debug"
      resValue("string", "app_name", "[Dev]Currency")
    }

    getByName("release") {
      isMinifyEnabled = true
      isDebuggable = false
      isShrinkResources = true
      //signingConfig = signingConfigs.getByName("release")
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
      resValue("string", "app_name", "Currency")
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
  implementation(project(":base"))
  implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

  coreLibraryDesugaring(CommonLibs.desugar_lib)

  implementation(Kotlin.stdblib_jdk)
  implementation(KotlinCoroutine.android)

  //AndroidX
  implementation(AndroidXAppCompat.app_compat)
  implementation(AndroidXCore.core_ktx)
  implementation(AndroidXActivity.activity_ktx)
  implementation(AndroidXFragment.fragment_ktx)
  androidxFragment()
  androidXArch()

  //Material
  implementation(Material.material)

  //Constraint Layout
  implementation(AndroidXConstraintLayout.constraint_layout)

  //multi dex
  implementation(AndroidXMultiDex.multi_dex)

  //timber
  implementation(CommonLibs.timber)

  //Test
  testImplementation(CommonLibs.junit)
  mockito()
  mockitoAndroid()
  androidXTest()
  androidXEspresso()

  //koin for di
  koinAndroid()

}