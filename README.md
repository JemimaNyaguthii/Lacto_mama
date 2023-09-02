# LactoMama Mobile App
 The LactoMama App has been designed to facilitate lactionist specialist and the mother interactions.This app entails the booking of appointments,tracking baby feeding,courses and educational materials.
# Getting Started
Follow these steps to set up and engage with the LactoMama App:
Clone the Repository
```sh
git clone https://github.com/akirachix/Ajolla-Mobile.git
```
Navigate to the Project Directory
```sh
cd Ajolla-Mobile
```

Build and Test
```
* ./gradlew clean build
```

# Install Dependencies in the gradles
Enable view bindings in the project directory

 Open the `build.gradle` (app-level) file in your Android project.
 Within the `android` block, add the following code to enable View Binding:
   ```gradle
    dependencies {
        implementation("androidx.core:core-ktx:1.10.1")
        implementation ("androidx.appcompat:appcompat:1.6.1")
        implementation ("com.google.android.material:material:1.9.0")
        implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
        testImplementation ("junit:junit:4.13.2")
        androidTestImplementation ("androidx.test.ext:junit:1.1.5")
        androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
        implementation ("androidx.activity:activity-ktx:1.7.2")
}