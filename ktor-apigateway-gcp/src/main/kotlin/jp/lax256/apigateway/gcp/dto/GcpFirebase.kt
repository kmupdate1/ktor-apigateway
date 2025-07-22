package jp.lax256.apigateway.gcp.dto

import jp.lax256.apigateway.core.contract.dto.Firebase
import jp.lax256.apigateway.core.contract.dto.Identities
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GcpFirebase(
    @SerialName("identities") override val identities: Identities,
    @SerialName("sign_in_provider") override val signInProvider: String,
): Firebase
