package com.example.navigation.NavigatorForActivities

import android.app.Activity
import androidx.annotation.NonNull

object Navigator {

    /**
     * Opens ActivityTwo
     *
     * @param activity an [Activity] needed to open the destiny activity
     */
    fun navigateToActivityTwo(@NonNull activity: Activity) {
        val intentToLaunch = ActivityOne.getCallingIntent(activity)
        activity.startActivity(intentToLaunch)
    }


    /**
     * Opens ActivityOne
     *
     * @param activity an [Activity] needed to open the destiny activity
     */
    fun navigateToActivityOne(@NonNull activity: Activity) {
        val intentToLaunch = ActivityTwo.getCallingIntent(activity)
        activity.startActivity(intentToLaunch)
    }
}