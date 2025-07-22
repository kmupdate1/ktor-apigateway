package jp.lax256.apigateway.gcp.dto

import jp.lax256.apigateway.core.contract.dto.Firebase
import jp.lax256.apigateway.core.contract.dto.JwtPayload
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GcpJwtPayload(
    @SerialName("iss") override val issuer: String,
    @SerialName("aud") override val audience: String,
    @SerialName("auth_time") override val authTime: Long,
    @SerialName("user_id") override val userId: String,
    @SerialName("sub") override val subject: String,
    @SerialName("iat") override val issuedAt: Long,
    @SerialName("exp") override val expired: Long,
    @SerialName("email") override val email: String,
    @SerialName("email_verified") override val emailVerified: Boolean,
    @SerialName("firebase") override val firebase: Firebase,
): JwtPayload
