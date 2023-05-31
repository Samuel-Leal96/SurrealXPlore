package com.example.surrealxplore.data.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.surrealxplore.data.MuseoApp
import com.example.surrealxplore.data.adapters.CiudadVallesAdapter
import com.example.surrealxplore.data.entity.MuseoDao
import com.example.surrealxplore.databinding.FragmentCiudadVallesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CiudadVallesFragment : Fragment() {

    private lateinit var myApp: MuseoApp
    private lateinit var museoDao: MuseoDao

    private lateinit var binding: FragmentCiudadVallesBinding
    private lateinit var museoAdapter: CiudadVallesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCiudadVallesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myApp = requireActivity().application as MuseoApp
        museoDao = myApp.museoDao.MuseoDao()

        setupRecyclerView()
        //loadMuseumsFromDatabase()

    }

    private fun setupRecyclerView() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val museosList = museoDao.obtenerMuseosPorCiudad("Ciudad Valles")

                withContext(Dispatchers.Main){
                    val rvMuseo = binding.recyclerView
                    rvMuseo.layoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)

                    val ciudadVallesAdapter = CiudadVallesAdapter(requireContext())
                    rvMuseo.adapter = ciudadVallesAdapter
                    ciudadVallesAdapter.submitList(museosList)
                    Log.d("ciudadValles", museosList.toString())
                }
            }
        }
    }

    private fun loadMuseumsFromDatabase() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val museosList = museoDao.obtenerMuseosPorCiudad("Ciudad Valles")
                Log.d("ciudadValles", museosList.toString())
            }
        }
    }

}