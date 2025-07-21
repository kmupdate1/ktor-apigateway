package jp.lax256.apigateway.gcp.dto

import jp.lax256.apigateway.core.contract.dto.JwtPayload
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GcpJwtPayload(
    @SerialName("iss") val issuer: String,
    @SerialName("aud") val audience: String,
    @SerialName("auth_time") val authTime: Long,
    @SerialName("user_id") val userId: String,
    @SerialName("sub") val subject: String,
    @SerialName("iat") val iat: Long,
    @SerialName("exp") val expired: Long,
    @SerialName("email") val email: String,
    @SerialName("email_verified") val emailVerified: Boolean,
    @SerialName("firebase") val firebase: Firebase,
): JwtPayload
