import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val address: Address,
    val birthDate: String,
    val company: Company,
    val email: String,
    val firstname: String,
    val id: Int,
    val lastname: String,
    val login: Login,
    val phone: String,
    val website: String
)
@Serializable
data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)
@Serializable
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)
@Serializable
data class Login(
    val md5: String,
    val password: String,
    val registered: String,
    val sha1: String,
    val username: String,
    val uuid: String
)
@Serializable
data class Geo(
    val lat: String,
    val lng: String
)
fun main() {
    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://jsonplaceholder.org/users"))
        .GET()
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    val jsonBody = response.body()
    val users: List<User> = Json.decodeFromString(jsonBody)

    println("Escoje un usuario de 1 a 30")
    var n = readln().toInt()
    println("Datos del usuario $n")
    var usuarioN= users.filter { users -> users.id == n }
        .forEach { user -> println(
            "Nombre: ${user.firstname} ${user.lastname}, Email: ${user.email}, Dirección: ${user.address.street}, ${user.address.city}, ${user.address.zipcode}, " +
            "Telefono: ${user.phone}, Página web: ${user.website}, " + "Compañia: ${user.company.name}, Tipo: ${user.company.bs}"
        )}
    n = readln().toInt()
}
