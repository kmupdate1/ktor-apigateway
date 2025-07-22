package jp.lax256.apigateway.core.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("sign_in_provider")
sealed interface Firebase {
    val identities: Identities
    val signInProvider: String
}

@Serializable
@SerialName("password")
data class FirebasePasswordAuth(
    @SerialName("identities") override val identities: Identities,
    @SerialName("sign_in_provider") override val signInProvider: String,
): Firebase

@Serializable
@SerialName("google.com")
data class FirebaseGoogleAuth(
    @SerialName("identities") override val identities: Identities,
    @SerialName("sign_in_provider") override val signInProvider: String,
): Firebase

@Serializable
@SerialName("facebook.com")
data class FirebaseFacebookAuth(
    @SerialName("identities") override val identities: Identities,
    @SerialName("sign_in_provider") override val signInProvider: String,
): Firebase

@Serializable
@SerialName("apple.com")
data class FirebaseAppleAuth(
    @SerialName("identities") override val identities: Identities,
    @SerialName("sign_in_provider") override val signInProvider: String,
): Firebase
