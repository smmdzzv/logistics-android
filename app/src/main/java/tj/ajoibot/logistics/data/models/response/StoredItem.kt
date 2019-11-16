package tj.ajoibot.logistics.data.models.response
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class StoredItem(
    @Json(name = "code")
    val code: String = "",
    @Json(name = "created_at")
    val createdAt: String = "",
    @Json(name = "deleted_at")
    val deletedAt: Any? = Any(),
    @Json(name = "deleted_by_id")
    val deletedById: Any? = Any(),
    @Json(name = "id")
    val id: String = "",
    @Json(name = "info")
    val info: Info = Info(),
    @Json(name = "pivot")
    val pivot: Pivot = Pivot(),
    @Json(name = "storage_history")
    val storageHistory: Any? = Any(),
    @Json(name = "stored_item_info_id")
    val storedItemInfoId: String = "",
    @Json(name = "updated_at")
    val updatedAt: Any? = Any()
)

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "branch_id")
    val branchId: String = "",
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "created_at")
    val createdAt: String = "",
    @Json(name = "customs_code")
    val customsCode: CustomsCode = CustomsCode(),
    @Json(name = "customs_code_id")
    val customsCodeId: String = "",
    @Json(name = "height")
    val height: Int = 0,
    @Json(name = "id")
    val id: String = "",
    @Json(name = "item")
    val item: Item = Item(),
    @Json(name = "item_id")
    val itemId: String = "",
    @Json(name = "length")
    val length: Int = 0,
    @Json(name = "order_id")
    val orderId: String = "",
    @Json(name = "owner")
    val owner: Owner = Owner(),
    @Json(name = "ownerId")
    val ownerId: String = "",
    @Json(name = "placeCount")
    val placeCount: Int = 0,
    @Json(name = "updated_at")
    val updatedAt: Any? = Any(),
    @Json(name = "weight")
    val weight: Int = 0,
    @Json(name = "width")
    val width: Int = 0
)

@JsonClass(generateAdapter = true)
data class CustomsCode(
    @Json(name = "calculateByPiece")
    val calculateByPiece: Boolean = false,
    @Json(name = "code")
    val code: String = "",
    @Json(name = "created_at")
    val createdAt: String = "",
    @Json(name = "deleted_at")
    val deletedAt: Any? = Any(),
    @Json(name = "id")
    val id: String = "",
    @Json(name = "internationalName")
    val internationalName: Any? = Any(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "percentage")
    val percentage: String = "",
    @Json(name = "price")
    val price: String = "",
    @Json(name = "rate")
    val rate: String = "",
    @Json(name = "shortName")
    val shortName: String = "",
    @Json(name = "updated_at")
    val updatedAt: String = "",
    @Json(name = "vat")
    val vat: String = ""
)

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "applyDiscount")
    val applyDiscount: Boolean = false,
    @Json(name = "created_at")
    val createdAt: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "onlyAgreedPrice")
    val onlyAgreedPrice: Boolean = false,
    @Json(name = "onlyCustomPrice")
    val onlyCustomPrice: Boolean = false,
    @Json(name = "tariff")
    val tariff: Tariff = Tariff(),
    @Json(name = "tariffId")
    val tariffId: String = "",
    @Json(name = "unit")
    val unit: String = "",
    @Json(name = "updated_at")
    val updatedAt: String = ""
)

@JsonClass(generateAdapter = true)
data class Tariff(
    @Json(name = "created_at")
    val createdAt: String = "",
    @Json(name = "deleted_at")
    val deletedAt: Any? = Any(),
    @Json(name = "description")
    val description: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "updated_at")
    val updatedAt: String = ""
)

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "branch_id")
    val branchId: String = "",
    @Json(name = "code")
    val code: String = "",
    @Json(name = "created_at")
    val createdAt: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "email_verified_at")
    val emailVerifiedAt: Any? = Any(),
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "phone")
    val phone: Any? = Any(),
    @Json(name = "position_id")
    val positionId: Any? = Any(),
    @Json(name = "updated_at")
    val updatedAt: String = ""
)

@JsonClass(generateAdapter = true)
data class Pivot(
    @Json(name = "created_at")
    val createdAt: String = "",
    @Json(name = "stored_item_id")
    val storedItemId: String = "",
    @Json(name = "trip_id")
    val tripId: String = "",
    @Json(name = "updated_at")
    val updatedAt: String = ""
)