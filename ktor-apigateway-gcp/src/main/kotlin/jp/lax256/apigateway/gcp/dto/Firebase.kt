package jp.lax256.apigateway.gcp.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Firebase(
    @SerialName("identities") val identities: Identities,
    @SerialName("sign_in_provider") val signInProvider: String,
)
