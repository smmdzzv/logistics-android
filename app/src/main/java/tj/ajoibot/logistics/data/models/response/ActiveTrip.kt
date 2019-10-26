package tj.ajoibot.logistics.data.models.response
import com.squareup.moshi.Json


data class ActiveTrip(
    @Json(name = "carId")
    val carId: String = "",
    @Json(name = "code")
    val code: String = "",
    @Json(name = "created_at")
    val createdAt: String = "",
    @Json(name = "departureAt")
    val departureAt: String = "",
    @Json(name = "departureDate")
    val departureDate: String = "",
    @Json(name = "driverId")
    val driverId: String = "",
    @Json(name = "from_consumption_id")
    val fromConsumptionId: String = "",
    @Json(name = "hasTrailer")
    val hasTrailer: Boolean = false,
    @Json(name = "id")
    val id: String = "",
    @Json(name = "returnDate")
    val returnDate: String = "",
    @Json(name = "returnedAt")
    val returnedAt: Any = Any(),
    @Json(name = "status")
    val status: String = "",
    @Json(name = "to_consumption_id")
    val toConsumptionId: String = "",
    @Json(name = "updated_at")
    val updatedAt: String = ""
)