package tj.ajoibot.logistics.data.models.response
import com.squareup.moshi.Json



data class AuthorizedUserResponse(
    @Json(name = "branch")
    val branch: Branch = Branch(),
    @Json(name = "branch_id")
    val branchId: String = "",
    @Json(name = "code")
    val code: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "email_verified_at")
    val emailVerifiedAt: Any = Any(),
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "phone")
    val phone: Any = Any(),
    @Json(name = "position_id")
    val positionId: String = ""
)

data class Branch(
    @Json(name = "country")
    val country: String = "",
    @Json(name = "director")
    val director: Any = Any(),
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = ""
)
