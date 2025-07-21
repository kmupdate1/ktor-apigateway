package jp.lax256.apigateway.gcp.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Identities(
    @SerialName("email") val email: List<String>,
)
