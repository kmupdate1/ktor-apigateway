package jp.lax256.apigateway.core.contract.dto

import kotlinx.serialization.Serializable

@Serializable
interface Identities {
    val email: List<String>
}
