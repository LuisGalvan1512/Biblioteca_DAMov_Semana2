package biblioteca

// Interfaces simples
interface Prestable {
    fun prestar()
    fun devolver()
}

interface Consultable {
    fun consultar()
}

// Clase abstracta base
abstract class Material(
    open var titulo: String,
    open var año: Int
) {
    abstract val tipo: String

    // Propiedad calculada con getter
    val antiguedad: Int
        get() = 2024 - año

    // Propiedad con getter y setter personalizado
    var disponible: Boolean = true
        set(value) {
            field = value
            if (value) {
                println("📖 $titulo ahora está disponible")
            } else {
                println("📚 $titulo ha sido prestado")
            }
        }

    init {
        println("Registrando material: $titulo ($año)")
    }

    open fun mostrarInfo() {
        println("Título: $titulo")
        println("Tipo: $tipo")
        println("Año: $año (${antiguedad} años de antigüedad)")
        println("Estado: ${if (disponible) "Disponible" else "Prestado"}")
    }
}