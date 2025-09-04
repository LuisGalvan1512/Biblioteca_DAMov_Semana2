package biblioteca

fun crearMateriales(): List<Material> {
    println("📚 === CREANDO MATERIALES ===")

    val materiales = mutableListOf<Material>()

    // Libros con constructor principal
    materiales.add(Libro("El Quijote", 1605, "Cervantes", 863))
    materiales.add(Libro("1984", 1949, "George Orwell", 328))

    // Libro con constructor secundario
    materiales.add(Libro("Hamlet", "Shakespeare"))

    // Revistas
    materiales.add(Revista("National Geographic", 2023, 145, "Enero"))
    materiales.add(Revista("Ciencia Hoy", 89)) // Constructor secundario

    // DVDs
    materiales.add(DVD("El Padrino", 1972, "Francis Ford Coppola", 175))
    materiales.add(DVD("Inception", 2010, "Christopher Nolan", 148))

    println("=".repeat(40))
    return materiales
}

fun demostrarGettersSetters(materiales: List<Material>) {
    println("🔧 === DEMO: GETTERS Y SETTERS ===")

    val libro = materiales.first { it is Libro }

    println("Estado inicial:")
    println("${libro.titulo} - Disponible: ${libro.disponible}")

    // Usar el setter personalizado
    println("\nCambiando disponibilidad:")
    libro.disponible = false  // Activa el setter
    libro.disponible = true   // Activa el setter nuevamente

    // Mostrar propiedad calculada
    println("\nPropiedades calculadas:")
    materiales.forEach { material ->
        println("${material.titulo}: ${material.antiguedad} años de antigüedad")
    }

    println("=".repeat(40))
}

fun demostrarPolimorfismo(materiales: List<Material>) {
    println("🔄 === DEMO: POLIMORFISMO ===")

    println("Todos los materiales pueden mostrar información:")
    materiales.take(3).forEach { material ->
        println("\n--- ${material.titulo} ---")
        material.mostrarInfo() // Polimorfismo: cada tipo muestra diferente info
    }

    println("\nOperaciones específicas por interfaz:")
    materiales.forEach { material ->
        print("${material.titulo}: ")
        when (material) {
            is Prestable -> println("Se puede prestar")
            is Consultable -> println("Solo consulta")
        }
    }

    println("=".repeat(40))
}

fun simularOperaciones(biblioteca: Biblioteca) {
    println("🏛️ === SIMULANDO OPERACIONES ===")

    // Buscar y prestar un libro
    val libro = biblioteca.buscarPorTitulo("1984")
    if (libro is Prestable) {
        libro.prestar()
    }

    // Consultar una revista
    val revista = biblioteca.buscarPorTitulo("Geographic")
    if (revista is Consultable) {
        revista.consultar()
    }

    // Prestar un DVD
    val dvd = biblioteca.buscarPorTitulo("Padrino")
    if (dvd is Prestable) {
        dvd.prestar()
    }

    // Intentar prestar algo ya prestado
    if (libro is Prestable) {
        libro.prestar() // Debe fallar
    }

    // Devolver
    if (libro is Prestable) {
        libro.devolver()
    }

    println("=".repeat(40))
}

fun demostrarHerencia() {
    println("🏛️ === DEMO: HERENCIA ===")

    // Biblioteca normal
    val bibliotecaNormal = Biblioteca("Biblioteca Central")

    // Biblioteca universitaria (herencia)
    val bibliotecaUni = BibliotecaUniversitaria(
        "Biblioteca Académica",
        "Campus Universitario",
        "Universidad Nacional"
    )

    // Agregar algunos materiales
    bibliotecaUni.agregarMaterial(Libro("Cálculo", 2020, "Stewart", 1200))
    bibliotecaUni.agregarMaterial(Libro("Física", 2019, "Halliday", 800))

    // Método específico de la subclase
    bibliotecaUni.generarReporteAcademico()

    // Método sobrescrito
    bibliotecaUni.mostrarEstadisticas()

    println("=".repeat(40))
}

fun main() {
    println("📚 SISTEMA SIMPLE DE BIBLIOTECA")
    println("=".repeat(50))

    // 1. Crear materiales (constructores)
    val materiales = crearMateriales()

    // 2. Crear biblioteca
    val miBiblioteca = Biblioteca("Biblioteca Municipal", "Calle Principal 123")

    // 3. Agregar materiales
    materiales.forEach { miBiblioteca.agregarMaterial(it) }

    // 4. Demostrar getters y setters
    demostrarGettersSetters(materiales)

    // 5. Demostrar polimorfismo
    demostrarPolimorfismo(materiales)

    // 6. Simular operaciones de biblioteca
    simularOperaciones(miBiblioteca)

    // 7. Mostrar catálogo y estadísticas
    miBiblioteca.mostrarCatalogo()
    miBiblioteca.mostrarEstadisticas()

    // 8. Demostrar herencia
    demostrarHerencia()

    println("\n✅ DEMO COMPLETADA")
    println("\nConceptos demostrados:")
    println("✓ Clases y objetos")
    println("✓ Constructores primarios y secundarios")
    println("✓ Getters y setters personalizados")
    println("✓ Clases abstractas")
    println("✓ Interfaces")
    println("✓ Herencia y sobrescritura")
    println("✓ Polimorfismo")
}