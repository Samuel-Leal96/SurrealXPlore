package com.example.surrealxplore

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.example.surrealxplore.data.MuseoApp
import com.example.surrealxplore.data.entity.MuseoDao
import com.example.surrealxplore.data.entity.MuseoEntity
import com.example.surrealxplore.data.view.CiudadVallesFragment
import com.example.surrealxplore.data.view.XilitlaFragment
import com.example.surrealxplore.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var myApp: MuseoApp
    private lateinit var museoDao: MuseoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.tvCiudadValles.setTextColor(getColor(R.color.black))
                binding.tvXilitla.setTextColor(getColor(R.color.gold_subtitle))
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.tvCiudadValles.setTextColor(getColor(R.color.white))

                binding.tvXilitla.setTextColor(getColor(R.color.gold_subtitle))
            }
        }

        myApp = applicationContext as MuseoApp
        museoDao = myApp.museoDao.MuseoDao()

        //Insertamos la info a la base de datos
        llenarBaseDeDatos()

        lifecycleScope.launch {
            delay(1700)
                mostrarMuseos()
        }

    }

    private fun llenarBaseDeDatos(){

        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val museoList = mutableListOf<MuseoEntity>()

                val museoTamauntzan = listOf(R.drawable.tamuantzan1, R.drawable.tamuantzan2, R.drawable.tamuantzan3)
                val museoHuasteco = listOf(R.drawable.regional_huasteco1, R.drawable.regional_huasteco2, R.drawable.regional_huasteco3)
                val museoEdward = listOf(R.drawable.james1, R.drawable.james2, R.drawable.james3)
                val museoLeonora = listOf(R.drawable.leonora1, R.drawable.leonora2, R.drawable.leonora3)

                val museos = arrayListOf(
                    "Museo de las Culturas de la Huasteca Potosina Tamuantzán",
                    "Museo Regional Huasteco",
                    "Museo Edward James",
                    "Museo Leonora Carrington Xilitla"
                )

                museos.forEach { museo ->
                    when(museo){
                        "Museo de las Culturas de la Huasteca Potosina Tamuantzán"->{
                            //Museo Museo de las Culturas de la Huasteca Potosina Tamuantzán
                            val museo1 = MuseoEntity(
                                nombre = "Museo de las Culturas de la Huasteca Potosina Tamuantzán",
                                calle = "Carr. México-Laredo y Libramiento Sur",
                                colonia = "Fracc. Lomas de San José",
                                cp = "CP 79090",
                                ciudad = "Ciudad Valles",
                                estado = "San Luis Potosí",
                                telefono = "Tels.: (481) 381 26 75",
                                horario = "Abierto al público de lunes a viernes de 9 a 16 hrs.",
                                costos = "Entrada general: \$10\n" +
                                        "Niños: \$5, miembros del INAPAM entrada libre",
                                descripcion = "El museo forma parte del conjunto que alberga el Centro Cultural de la Huasteca. Su acervo se conformó con piezas encontradas en las zonas arqueológicas de Tamtoc y Tamohi. Cuenta con una sala arqueológica, donde se puede observar una colección de piezas que fueron encontradas principalmente en los sitios ya mencionados, como la reproducción del Adolescente Huasteco. En la sala etnográfica las piezas están agrupadas por temas: vestuario y textiles, música y danza y objetos de uso común, como ceremoniales, instrumentos de labranza, entre otras piezas.\n" +
                                        "\n" +
                                        "Cuenta con una colección arqueológica, histórica y etnográfica de la cultura huasteca. Destaca el importante acervo de instrumentos musicales de la región.",
                                fecha_fundacion = "23 de enero de 1997",
                                servicios = "Visitas guiadas",
                                imagen = museoTamauntzan
                            )

                            museoList.add(museo1)
                        }

                        "Museo Regional Huasteco" -> {
                            //Museo Museo de las Culturas de la Huasteca Potosina Tamuantzán
                            val museo2 = MuseoEntity(
                                nombre = "Museo Regional Huasteco",
                                calle = "Rotarios 623 esq. Artes",
                                colonia = "Col. Rotarios",
                                cp = "CP 79080",
                                ciudad = "Ciudad Valles",
                                estado = "San Luis Potosí",
                                telefono = "Tels.: (481) 381 14 48, 382 18 32, 381 50 00",
                                horario = "Abierto al público de lunes a viernes de 9 a 17 hrs.\n" +
                                        "sábado y domingo cerrado",
                                costos = "Entrada gratuita.",
                                descripcion = "En el Museo Regional Huasteco A.C. se resguardan algunos de los más valiosos vestigios de la Cultura Huasteca.\n" +
                                        "\n" +
                                        "Preserva y exhibe los testimonios arqueológicos de la cultura huasteca, la cual tuvo su desarrollo durante el periodo previo a la conquista española. Entre las piezas más importantes de la colección destacan un pectoral de concha con la representación del dios mexica del viento, una mazorca con cabeza humana, así como diversas figuras elaboradas en barro. La exhibición muestra malacates, sellos y figuras diversas; representaciones que evocan sacerdotes, dioses y motivos cosmogónicos. También hay cuchillos para el sacrificio, hachas para la talla de la madera y navajas de obsidiana. La muestra de vasijas presentadas al público presenta dibujos de personajes y animales. El museo cuenta con una sección dedicada a la indumentaria tradicional utilizada por las distintas comunidades téenek de la región. El inmueble fue diseñado por el arquitecto Luis Angulo e inaugurado el 31 de diciembre de 1978, gracias a las gestiones de la señora Oralia Gutiérrez de Sánchez, quien consiguió un terreno de propiedad federal e inició la recopilación del acervo.\n" +
                                        "\n" +
                                        "Fue en el año de 1963 cuando, después de conseguir varias piezas importantes, la maestra Oralia decidió pedir un permiso al Instituto Nacional de Antropología e Historia para la construcción del museo en Cd. Valles; el permiso se le concedió de inmediato y pronto ella se enfrascó en la lucha por la construcción del museo. Consiguió la donación de un terreno en la calle Rotarios y exponía las piezas en su mismo domicilio para conseguir donativos para la construcción, que consiguió completar quince años después, durante los cuales la única ayuda que recibió de parte del gobierno fue para la construcción del techo y las ventanas del inmueble. Así, con una acervo de más de diez mil quinientas piezas arqueológicas, se inauguró el 31 de diciembre de 1978 el primer museo de Cd. Valles, el Museo Regional Huasteco A. C.",
                                fecha_fundacion = "31 de diciembre de 1978",
                                servicios = "Biblioteca\n" +
                                        "Salón de conferencias\n" +
                                        "Visitas guiadas",
                                imagen = museoHuasteco
                            )

                            museoList.add(museo2)

                        }

                        "Museo Edward James" -> {
                            //Museo Eduard James
                            val museo3 = MuseoEntity(
                                nombre = "Museo Edward James",
                                calle = "Camino a las Pozas 1",
                                colonia = "Barrio La Conchita",
                                cp = "CP 79900",
                                ciudad = "Xilitla, Xilitla",
                                estado = "San Luis Potosí",
                                telefono = "Tels.: 489 127 7573",
                                horario = "Lunes a domingo de 8 a 18 hrs.",
                                costos = "Entrada general \$100; \$50 para Xilitlenses, niños de cuatro a 10 años, estudiantes, maestros y adultos mayores.",
                                descripcion = "Fue inaugurado el 22 de diciembre de 2022. Ubicado en el Centro James, un complejo turístico innovador del empresario mexicano Mario César Ramírez y Plutarco Gastélum Llamazares.\n" +
                                        "\n" +
                                        "Muestra la vida de James, visionario, poeta y una de las figuras clave del mecenazgo en el siglo XX, fue sin duda Edward James, dueño de la colección surrealista más importante de su tiempo. El apoyo que James dio a artistas como Salvador Dalí, René Magritte y Leonora Carrington fue fundamental para sus trayectorias.\n" +
                                        "\n" +
                                        "También narra la vida del personaje acompañado por sus libros, fotografías, postales, cartas y videos. Destacan 14 moldes originales de madera usados en la construcción del jardín surrealista, verdaderas obras de arte por derecho propio, que transformaron la visión de James en una realidad tangible. Entre las obras que exhibe destacan: un sofá en forma de labios, y el teléfono langosta.\n" +
                                        "\n" +
                                        "El recinto rinde homenaje a los artesanos y trabajadores que llevaron a cabo esta labor titánica a lo largo de los años, destacando la figura del maestro ebanista José Aguilar. Finalmente, el recorrido culmina con la experiencia multimedia Seclusia Immersive Experience, un viaje por la mente del visionario y poeta inglés, producida por Nullpixel. Cuenta con cafetería, una poza con alberca y un acceso conectado con el laberinto escultórico de Xilitla.",
                                fecha_fundacion = "22 de diciembre de 2022",
                                servicios = "",
                                imagen = museoEdward
                            )

                            museoList.add(museo3)
                        }

                        "Museo Leonora Carrington Xilitla" -> {
                            //Museo Eduard James
                            val museo4 = MuseoEntity(
                                nombre = "Museo Leonora Carrington Xilitla",
                                calle = "Miguel Álvarez Acosta 109",
                                colonia = "Centro",
                                cp = "CP 79900",
                                ciudad = "Xilitla, Xilitla",
                                estado = "San Luis Potosí",
                                telefono = "Tels.: 489 365 04 42",
                                horario = "Martes a domingo de 11 a 17 hrs",
                                costos = "Entrada general \$50\n" +
                                        "\$25 estudiantes, maestros y adultos mayores (mostrando credencial vigente)\n" +
                                        "Acceso libre para los residentes de Xilitla",
                                descripcion = "La colección a exhibir se compone por 63 esculturas de pequeño y mediano formato, algunos grabados, dos tapices de lana, 25 máscaras en bronce y algunas herramientas de trabajo de la artista.\n" +
                                        "\n" +
                                        "El Museo comprende dos partes; la parte frontal, el inmueble conserva una construcción que fue una vivienda por la calle Corregidora, con un área construida de 283 m2, adaptados como oficinas, tienda del museo, sala de Interpretación del surrealismo, sala biográfica de la artista Leonora Carrington, servicios sanitarios y bodega.\n" +
                                        "\n" +
                                        "La sala de Interpretación del surrealismo, contiene material explicativo del origen de este movimiento artístico y la migración de varios artistas a México, sobre todo mujeres referentes de este concepto. En la sala biográfica de Leonora se presentan videos relacionados su vida y obra, así como fotografías, documentos y algunos objetos pertenecientes a la artista. Estos espacios se complementarán con la museografía y mobiliario especializado para su funcionamiento, con instalaciones de luz escénica, video e internet para el manejo de pantallas y tabletas interactivas.",
                                fecha_fundacion = "19 de octubre de 2018",
                                servicios = "El museo es un edificio que consta de dos pisos, una terraza, una cafetería y 3 salas y al ser un espacio incluyente, tendrá un elevador para personas con alguna discapacidad.",
                                imagen = museoLeonora
                            )
                            museoList.add(museo4)
                        }
                    }
                }

                museoList.forEach { museo ->
                    val existeMuseo =
                        museoDao.obtenerMuseosPorNombre(museo.nombre)

                    if (existeMuseo.isEmpty()) {
                        museoDao.insertMuseos(museoList)
                    }
                }
            }
        }

    }

    private fun mostrarMuseos() {
        val btnValles = binding.tvCiudadValles
        val btnXilitla = binding.tvXilitla

        irACiudadVallesFragment()

        btnValles.setOnClickListener {
            irACiudadVallesFragment()
        }

        btnXilitla.setOnClickListener {
            irAXilitlaFragment()
        }

    }

    private fun irACiudadVallesFragment() {

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.tvCiudadValles.setTextColor(getColor(R.color.black))
                binding.tvXilitla.setTextColor(getColor(R.color.gold_subtitle))
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.tvCiudadValles.setTextColor(getColor(R.color.white))
                binding.tvXilitla.setTextColor(getColor(R.color.gold_subtitle))
            }
        }

        val frgCiudadValles = supportFragmentManager.beginTransaction()
        frgCiudadValles.replace(R.id.contenedorMuseos, CiudadVallesFragment())
        frgCiudadValles.commit()
    }

    private fun irAXilitlaFragment() {

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.tvCiudadValles.setTextColor(getColor(R.color.gold_subtitle))
                binding.tvXilitla.setTextColor(getColor(R.color.black))
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.tvCiudadValles.setTextColor(getColor(R.color.gold_subtitle))
                binding.tvXilitla.setTextColor(getColor(R.color.white))
            }
        }

        val frgXilitla = XilitlaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorMuseos, frgXilitla)
            .commit()
    }
}