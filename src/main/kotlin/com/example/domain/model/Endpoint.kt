package com.example.domain.model

import com.example.domain.util.DomainConstants.AUTHORIZED_ENDPOINT
import com.example.domain.util.DomainConstants.DELETE_USER_ENDPOINT
import com.example.domain.util.DomainConstants.GET_USER_INFO_ENDPOINT
import com.example.domain.util.DomainConstants.ROOT_ENDPOINT
import com.example.domain.util.DomainConstants.SIGN_OUT_ENDPOINT
import com.example.domain.util.DomainConstants.TOKEN_VERIFICATION_ENDPOINT
import com.example.domain.util.DomainConstants.UNAUTHORIZED_ENDPOINT
import com.example.domain.util.DomainConstants.UPDATE_USER_INFO_ENDPOINT

sealed class Endpoint(val path: String) {

    object Root: Endpoint(path = ROOT_ENDPOINT)
    object TokenVerification: Endpoint(TOKEN_VERIFICATION_ENDPOINT)
    object GetUserInfo: Endpoint(path = GET_USER_INFO_ENDPOINT)
    object UpdateUserInfo: Endpoint(path = UPDATE_USER_INFO_ENDPOINT)
    object DeleteUser: Endpoint(path = DELETE_USER_ENDPOINT)
    object SignOut: Endpoint(path = SIGN_OUT_ENDPOINT)
    object Unauthorized: Endpoint(path = UNAUTHORIZED_ENDPOINT)
    object Authorized: Endpoint(path = AUTHORIZED_ENDPOINT)
}
