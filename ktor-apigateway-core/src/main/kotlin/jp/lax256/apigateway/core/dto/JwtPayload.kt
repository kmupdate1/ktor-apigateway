package jp.lax256.apigateway.core.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable// (JwtPayload.Companion.CustomJwtPayloadSerializer::class)
data class JwtPayload(
    @SerialName("iss") val issuer: String,
    @SerialName("aud") val audience: String,
    @SerialName("auth_time") val authTime: Long,
    @SerialName("user_id") val userId: String,
    @SerialName("sub") val subject: String,
    @SerialName("iat") val issuedAt: Long,
    @SerialName("exp") val expired: Long,
    @SerialName("email") val email: String,
    @SerialName("email_verified") val emailVerified: Boolean,
    @SerialName("firebase") val firebase: Firebase,

    val additionalClaims: Map<String, JsonElement> = emptyMap(),
    @SerialName("custom_extension")
    val customExtension: JsonObject = JsonObject(emptyMap()),
)
/* {
    companion object {
        private val standardClaim = setOf(
            "iss", "aud", "auth_time", "user_id", "sub", "iat", "exp",
            "email", "email_verified", "firebase", "custom_extension",
        )

        object CustomJwtPayloadSerializer: JsonTransformingSerializer<JwtPayload>(serializer()) {
            override fun transformDeserialize(element: JsonElement): JsonElement {
                if (element !is JsonObject) return element

                val transformedObject = buildJsonObject {
                    put("iss", element["iss"]?.jsonPrimitive ?: JsonPrimitive(""))
                    put("aud", element["aud"]?.jsonPrimitive ?: JsonPrimitive(""))
                    put("auth_time", element["auth_time"]?.jsonPrimitive ?: JsonPrimitive(0L))
                    put("user_id", element["user_id"]?.jsonPrimitive ?: JsonPrimitive(""))
                    put("sub", element["sub"]?.jsonPrimitive ?: JsonPrimitive(""))
                    put("iat", element["iat"]?.jsonPrimitive ?: JsonPrimitive(0L))
                    put("exp", element["exp"]?.jsonPrimitive ?: JsonPrimitive(0L))
                    put("email", element["email"]?.jsonPrimitive ?: JsonPrimitive(""))
                    put("email_verified", element["email_verified"]?.jsonPrimitive ?: JsonPrimitive(false))
                    put("firebase", element["firebase"]?.jsonObject ?: JsonObject(emptyMap()))

                    val customExtensionObject = element["custom_extension"]?.jsonObject ?: JsonObject(emptyMap())
                    put("customExtension", customExtensionObject)

                    val additionalClaimsMap = element.filterKeys { it !in standardClaim }
                    put("additionalClaims", buildJsonObject { /* put(additionalClaimsMap) */ })
                }

                return transformedObject
            }
        }
    }
}
*/
