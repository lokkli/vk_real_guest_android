package com.example.realguest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import com.example.realguest.databinding.VisitsFragmentBinding
import com.example.realguest.model.MyVisitsAdapter
import kotlinx.android.synthetic.main.visits_fragment.*

class VisitsFragment : Fragment() {
    lateinit var adapter: MyVisitsAdapter
    private var _binding: VisitsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VisitsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireView().findViewById<RecyclerView>(R.id.recyclerVisitList).apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            recyclerVisitList.layoutManager = layoutManager
        }
        viewModel.getVisits().observe(viewLifecycleOwner) { visits ->
            adapter = MyVisitsAdapter(
                requireActivity().applicationContext,
                visits.content as MutableList
            ).apply { recyclerVisitList.adapter = this }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}