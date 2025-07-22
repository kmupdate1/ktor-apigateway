package jp.lax256.apigateway.core.contract.dto

import kotlinx.serialization.Serializable

@Serializable
interface Firebase {
    val identities: Identities
    val signInProvider: String
}
