package biblioteca

open class Biblioteca(
    open var nombre: String,
    open var direccion: String
) {

    protected val materiales = mutableListOf<Material>()

    init {
        println("=== BIBLIOTECA INICIALIZADA ===")
        println("Nombre: $nombre")
        println("Dirección: $direccion")
    }

    // Constructor secundario para biblioteca pequeña
    constructor(nombre: String) : this(nombre, "Dirección no especificada") {
        println("Biblioteca creada con dirección por defecto")
    }

    // Propiedades calculadas
    val totalMateriales: Int
        get() = materiales.size

    val materialesDisponibles: Int
        get() = materiales.count { it.disponible }

    val materialesPrestados: Int
        get() = materiales.count { !it.disponible }

    fun agregarMaterial(material: Material) {
        materiales.add(material)
        println("✅ ${material.titulo} agregado a $nombre")
    }

    fun buscarPorTitulo(titulo: String): Material? {
        return materiales.find {
            it.titulo.lowercase().contains(titulo.lowercase())
        }
    }

    open fun mostrarEstadisticas() {
        println("\n=== ESTADÍSTICAS DE $nombre ===")
        println("Total de materiales: $totalMateriales")
        println("Disponibles: $materialesDisponibles")
        println("Prestados: $materialesPrestados")

        // Estadísticas por tipo
        val tiposCount = materiales.groupingBy { it.tipo }.eachCount()
        println("Por tipo:")
        tiposCount.forEach { (tipo, cantidad) ->
            println("  $tipo: $cantidad")
        }
    }

    fun mostrarCatalogo() {
        println("\n=== CATÁLOGO COMPLETO ===")
        if (materiales.isEmpty()) {
            println("No hay materiales registrados")
            return
        }

        materiales.forEachIndexed { index, material ->
            println("\n--- Material ${index + 1} ---")
            material.mostrarInfo()
        }
    }

    fun realizarOperacionesDiarias() {
        println("\n=== OPERACIONES DEL DÍA ===")
        materiales.forEach { material ->
            when (material) {
                is Prestable -> {
                    println("${material.titulo}: Disponible para préstamo")
                }
                is Consultable -> {
                    println("${material.titulo}: Solo consulta en sala")
                }
            }
        }
    }
}

// Subclase especializada
class BibliotecaUniversitaria(
    override var nombre: String,
    override var direccion: String,
    var universidad: String
) : Biblioteca(nombre, direccion) {

    init {
        println("Biblioteca universitaria de: $universidad")
    }

    fun generarReporteAcademico() {
        println("\n=== REPORTE ACADÉMICO ===")
        println("Universidad: $universidad")

        val librosAcademicos = materiales.filterIsInstance<Libro>()
            .filter { it.año >= 2010 }

        println("Libros académicos recientes: ${librosAcademicos.size}")

        librosAcademicos.forEach { libro ->
            println("- ${libro.titulo} (${libro.autor}) - ${libro.año}")
        }
    }

    override fun mostrarEstadisticas() {
        super.mostrarEstadisticas()
        println("Universidad asociada: $universidad")
    }
}