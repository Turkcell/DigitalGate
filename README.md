# DIGITALGATE

## 1.Overview

Digitalgate provides integration to Turkcell login  systems. We have developed an SDK that is highly robust, secure, lightweight, configurable and very simple to embed.

You can initialize login , register, switch account flows.

The Digitalgate Android SDK is compatible with Android 4.3(API 18) and above.

## 2.Quick Start
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

