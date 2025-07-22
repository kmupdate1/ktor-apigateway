package jp.lax256.apigateway.gcp.dto

import jp.lax256.apigateway.core.contract.dto.Identities
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GcpIdentities(
    @SerialName("email") override val email: List<String>,
): Identities
