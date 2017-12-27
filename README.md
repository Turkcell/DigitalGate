# DIGITALGATE

## 1. Overview

Digitalgate provides integration to Turkcell login  systems. We have developed an SDK that is highly robust, secure, lightweight, configurable and very simple to embed.

You can initialize login , register, switch account flows.

The Digitalgate Android SDK is compatible with Android 4.3(API 18) and above.

## 2. Quick Start
### 2.1 Adding SDK to your Project

The simplest way to integrate the SDK into your project is by using Gradle’s Dependency Management.

Adding Digitalgate’s Android SDK Dependency:
1.	Open your project and then open your_app | build.gradle
2.	Add this to Module-level /app/build.gradle before dependencies:

```
    repositories {
        maven {
            url 'https://mymavenrepo.com/repo/ukAiuNSVkftQiB4kKUPH/'
            name 'Private Repo'
        }
    }
```

Add the compile dependency with the latest version of the Digitalgate SDK in the build.gradle file:

```
    compile ('com.turkcell.digitalgate:digitalgate-aar:4.3.0'){
        transitive = true
    }

```

### 2.2 Setting the Required Permissions

The AndroidManifest.xml should include the following permissions:

```
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <!-- Optional : -->
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
```

## 3. SDK Initialization

Initialization of the SDK is completed in two stages. In the first stage the DGLoginCoordinator is created by using Builder Pattern. When building  DGLoginCoordinator, appId is required where theme and language parameters are optional. In the second stage the call to one of the main flows(e.g. call startForLogin, startForSwitchAccount, startForRegister) is needed. 

To initialize the SDK, add the following code in your Application activity or fragment:

```
    DGLoginCoordinator dg = new DGLoginCoordinator.Builder().appId(app_id).build();
```

## 4. SDK Initialization

Having initialized the SDK, start login one of the main flows to call. It takes three boolean parameters;
* disableCellLogin: if true, cellular login functionality won’t work.
* autoLoginOnly: if true, only cellular login and remember me will work
* disableAutoLogin: if true, login process is forced to user.
