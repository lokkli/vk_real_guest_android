package com.example.realguest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realguest.common.Common.retrofitService
import com.example.realguest.databinding.VisitsFragmentBinding
import com.example.realguest.model.VisitAdapter
import kotlinx.android.synthetic.main.visits_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VisitsFragment : Fragment() {
    lateinit var visitAdapter: VisitAdapter
    private var _binding: VisitsFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VisitsFragmentBinding.inflate(inflater, container, false)
        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupView()
    }

    private fun setupList() {
        visitAdapter = VisitAdapter()
        recyclerVisitList.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            adapter = visitAdapter
        }
    }

    private fun setupView() {
        lifecycleScope.launch {
            viewModel.listData.collect { visitAdapter.submitData(it) }
        }
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(
                this, VisitViewModelFactory(retrofitService)
            )[MainViewModel::class.java]
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}