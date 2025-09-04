package biblioteca

// Subclase Libro
class Libro(
    override var titulo: String,
    override var año: Int,
    var autor: String,
    var paginas: Int
) : Material(titulo, año), Prestable, Consultable {

    override val tipo = "Libro"

    // Constructor secundario para libros clásicos
    constructor(titulo: String, autor: String) : this(titulo, 1900, autor, 200) {
        println("Libro clásico agregado con datos por defecto")
    }

    override fun prestar() {
        if (disponible) {
            disponible = false
            println("📖 Libro '$titulo' prestado por 15 días")
        } else {
            println("❌ '$titulo' no está disponible")
        }
    }

    override fun devolver() {
        disponible = true
        println("✅ Libro '$titulo' devuelto exitosamente")
    }

    override fun consultar() {
        println("📚 Consultando '$titulo' de $autor en sala de lectura")
    }

    override fun mostrarInfo() {
        super.mostrarInfo()
        println("Autor: $autor")
        println("Páginas: $paginas")
    }
}

// Subclase Revista
class Revista(
    override var titulo: String,
    override var año: Int,
    var numero: Int,
    var mes: String
) : Material(titulo, año), Consultable {

    override val tipo = "Revista"

    // Constructor secundario para revistas actuales
    constructor(titulo: String, numero: Int) : this(titulo, 2024, numero, "Actual") {
        println("Revista actual creada")
    }

    override fun consultar() {
        println("📰 Consultando revista '$titulo' #$numero ($mes $año)")
    }

    override fun mostrarInfo() {
        super.mostrarInfo()
        println("Número: $numero")
        println("Mes: $mes")
        println("⚠️ Solo consulta en biblioteca")
    }
}

// Subclase DVD
class DVD(
    override var titulo: String,
    override var año: Int,
    var director: String,
    var duracion: Int
) : Material(titulo, año), Prestable {

    override val tipo = "DVD"

    override fun prestar() {
        if (disponible) {
            disponible = false
            println("📀 DVD '$titulo' prestado por 7 días")
        } else {
            println("❌ '$titulo' no está disponible")
        }
    }

    override fun devolver() {
        disponible = true
        println("✅ DVD '$titulo' devuelto exitosamente")
    }

    override fun mostrarInfo() {
        super.mostrarInfo()
        println("Director: $director")
        println("Duración: $duracion minutos")
    }
}