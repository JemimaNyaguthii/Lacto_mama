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
   android {
       
       viewBinding {
           enabled = true
       }
       
   }