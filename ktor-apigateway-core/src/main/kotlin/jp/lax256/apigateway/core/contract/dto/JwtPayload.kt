package jp.lax256.apigateway.core.contract.dto

import kotlinx.serialization.Serializable

@Serializable
interface JwtPayload {
    val issuer: String
    val audience: String
    val authTime: Long
    val userId: String
    val subject: String
    val issuedAt: Long
    val expired: Long
    val email: String
    val emailVerified: Boolean
    val firebase: Firebase
}
