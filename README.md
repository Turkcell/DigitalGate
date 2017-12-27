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
    compile ('com.turkcell.digitalgate:digitalgate-aar:4.7.1'){
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

```Java
    DGLoginCoordinator dg = new DGLoginCoordinator.Builder().appId(your_app_id).build();
```

## 4. SDK Initialization

Having initialized the SDK, start login one of the main flows to call. It takes three boolean parameters;
* disableCellLogin: if true, cellular login functionality won’t work.
* autoLoginOnly: if true, only cellular login and remember me will work
* disableAutoLogin: if true, login process is forced to user.

```Java
    try {
            dg.startForLogin(this, disableCellLogin, autoLoginOnly, disableAutoLogin);
    } catch (DGException e) {
            //application error handling, e.g. required appId
    }
```

For getting result, see Section 7.

## 5. SDK Start Register

Having initialized the SDK, start register one of the main flows to call. It runs without parameters;

```Java
    try {
        dg.startForRegister(this);
    } catch (DGException e) {
        //application error handling, e.g. required appId
    }
```

For getting result, see Section 7.

## 6. SDK Switch Account

Having initialized the SDK, switch account one of the main flows to call. It runs without parameters;

```Java
    try {
        dg.startForSwitchAccount(this);
    } catch (DGException e) {
        //application error handling, e.g. required appId
    }
```

For getting result, see Section 7.

## 7. SDK Result

To get the result from SDK, onActivityResult method must be overridden in the application’s Activity or Fragment.

Add the following code in your Application’s fragment or activity:

```Java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DGLoginCoordinator.DG_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Unexpected Error Case
            }
            if (resultCode == Activity.RESULT_OK) {
                DGResult dgResult = DGLoginCoordinator.getDGResult(data);
                //dgResult has the result, take action according to result
            }
        }
    }
```

## 8. SDK Logout
To logout from the system, there is a static method to call. Re-initialization of DGLoginCoordinator is not needed. Add the following code in your Application:

```Java
    DGLoginCoordinator.logout(this, your_app_id);
```

For logout, there is no result. Having called the logout method is enough.

## 9. SDK Style Configuration
The configuration of the sdk can be achieved by creating DGTheme and passing DGTheme to DGLoginCoordinator. Builder pattern is used for creating the DGTheme. 

Sample code for configuring the style:

```Java
    DGTheme dgTheme = new DGTheme.Builder()
            .setBackgroundColor(android.R.color.holo_green_light)
            .setTitleLabelColor(android.R.color.holo_red_dark)
            .setDescriptionTextColor(android.R.color.holo_orange_dark)
            .setCheckBoxPassiveIcon(R.drawable.dg_checkbox_normal)
            .setPositiveButtonTextColor(android.R.color.black)
            .build();

    DGLoginCoordinator dg = new DGLoginCoordinator.Builder().theme(dgTheme).appId(your_app_id).build();
```

