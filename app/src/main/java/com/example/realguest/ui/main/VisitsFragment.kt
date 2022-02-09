package com.example.realguest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import com.example.realguest.common.Common.retrofitService
import com.example.realguest.common.Common.sharedPref
import com.example.realguest.databinding.FragmentProfileBinding
import com.example.realguest.model.MyVisitsAdapter
import com.example.realguest.model.Profile
import com.example.realguest.model.Visits
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VisitsFragment : Fragment() {
    lateinit var adapter: MyVisitsAdapter
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    // private val model = ViewModelProvider.of(this).get(ProfileViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitService.getProfile("Bearer " + sharedPref.getString("access_token", "")!!)
            .enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    val profile = response.body()!!
                    binding.username.text = profile.first_name
                    Picasso.get().load(profile.photo_100).into(binding.avatarId)
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                }
            })
        requireView().findViewById<RecyclerView>(R.id.recyclerVisitList).apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            recyclerVisitList.layoutManager = layoutManager
        }
        getVisitsPage()
    }

    private fun getVisitsPage() {
        retrofitService.getVisits("Bearer " + sharedPref.getString("access_token", "")!!)
            .enqueue(object : Callback<Visits> {
                override fun onFailure(call: Call<Visits>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<Visits>,
                    response: Response<Visits>
                ) {
                    if (response.isSuccessful) {
                        adapter = MyVisitsAdapter(
                            requireActivity().applicationContext,
                            response.body()!!.content as MutableList
                        )
                        adapter.notifyDataSetChanged()
                        recyclerVisitList.adapter = adapter
                    } else Toast.makeText(
                        requireActivity().applicationContext,
                        "Неудача",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}