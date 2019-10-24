package tj.ajoibot.ajoibotlogistics.data.models.request

data class Credentials(
    val email: String,
    val password: String
){
    fun validate(): Boolean {
        return this.email.isNotBlank() && this.password.isNotBlank()
    }
}