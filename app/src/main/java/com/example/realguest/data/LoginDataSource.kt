package com.example.realguest.data

import com.example.realguest.data.model.LoggedInUser
import java.lang.Exception

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        lateinit var fakeUser: LoggedInUser
        // TODO: handle loggedInUser authentication
        return Result.Error(Exception("TODO!"))
    }

    fun logout() {
        // TODO: revoke authentication
    }
}