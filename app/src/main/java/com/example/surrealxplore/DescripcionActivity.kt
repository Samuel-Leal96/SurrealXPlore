package com.example.surrealxplore

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import com.example.surrealxplore.data.MuseoApp
import com.example.surrealxplore.data.entity.MuseoDao
import com.example.surrealxplore.data.entity.MuseoEntity
import com.example.surrealxplore.databinding.ActivityDescripcionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class DescripcionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescripcionBinding

    private lateinit var myApp: MuseoApp
    private lateinit var museoDao: MuseoDao

    var name: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityDescripcionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtenemos el nombre del museo enviado por la interfaz
        name = intent.extras?.getString("name")

        myApp = applicationContext as MuseoApp
        museoDao = myApp.museoDao.MuseoDao()

        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val museo = museoDao.obtenerMuseosPorNombre(name.toString())

                withContext(Dispatchers.Main){
                    mostrarDescripcion(museo)
                }
            }
        }

    }

    fun mostrarDescripcion(museoObjeto: List<MuseoEntity>) {
        val museo = museoObjeto[0]

        //Instanciamos las variables para obtener la info de la base de datos
        val imagenes = museo.imagen
        val nombre = museo.nombre
        val descripcion = museo.descripcion
        val ubicacion = "${museo.calle}\n${museo.colonia}\n${museo.cp}\n${museo.ciudad}\n${museo.estado}\n"
        val horarios = museo.horario
        val fechaFundacion = museo.fecha_fundacion
        val servicios = museo.servicios
        val telefono = museo.telefono
        var carousel: ImageCarousel = binding.carousel

        //Mostramos la info en la vista xml
        binding.imgCabecera.setImageResource(imagenes[0])
        binding.tvNombreMuseo.text = nombre
        binding.tvDescripcion.text = descripcion
        binding.tvUbicacionMuseo.text = ubicacion
        binding.tvHorarios.text = horarios
        binding.tvServicios.text = servicios
        binding.tvTelefono.text = telefono
        binding.tvFechaFundacion.text = fechaFundacion


        //Configuracion del carousel de imagenes
        val list = mutableListOf<CarouselItem>()

        imagenes.forEach {
            list.add(
                CarouselItem(
                    it
                )
            )
        }
        carousel.addData(list)
    }

}