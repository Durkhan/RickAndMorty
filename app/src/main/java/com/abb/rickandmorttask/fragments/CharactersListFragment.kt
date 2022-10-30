package com.abb.rickandmorttask.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.abb.rickandmorttask.R
import com.abb.rickandmorttask.adabter.CharactersListAdapter
import com.abb.rickandmorttask.databinding.FragmentOfcharactersBinding
import com.abb.rickandmorttask.model.Character
import com.abb.rickandmorttask.retrofit.Repository
import com.abb.rickandmorttask.viewmodel.CharacterListViewModel
import com.abb.rickandmorttask.viewmodel.CharactersViewModelFactory


class CharactersListFragment: Fragment() {
    private var _binding:FragmentOfcharactersBinding?=null
    private val binding get() = _binding!!
    private val characterviewModel:CharacterListViewModel by activityViewModels{ CharactersViewModelFactory(
        Repository()
    )}
    private var loading = false
    private lateinit var staggeredGridLayoutManager:StaggeredGridLayoutManager
    private var characterslistadapter:CharactersListAdapter?=null
    private var  pastVisibleItem: Int=0
    private var  totalItems: Int=0
    private var  visibleItems: Int=0
    private var page:Int=1

    override fun onAttach(context: Context) {
        super.onAttach(context)
            characterviewModel.getCharacters(page)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentOfcharactersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getLivedataObserve()
        searchCharacterbyName()
        characterslistadapter= CharactersListAdapter(requireContext())

        binding.apply {
            swiperefresh.setOnRefreshListener {
                getLivedataObserve()
                swiperefresh.isRefreshing=false
            }
            btnFilter.setOnClickListener {
                findNavController().navigate(R.id.action_charactersListFragment_to_filterFragment)
            }


           txtReset.setOnClickListener {
               characterviewModel.name.value=" "
                characterviewModel.getCharacters(1)
                characterviewModel.filterValue.value = arrayOf(0,0,0)
                characterviewModel.filterValues.value= arrayOf("","","")
            }
        }
        characterviewModel.isFilter.observe(viewLifecycleOwner, {
            binding.txtReset.visibility = if (it) View.VISIBLE else View.GONE
        })

        //set adapter
        staggeredGridLayoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.recycleviewOfcharacterslist.layoutManager= staggeredGridLayoutManager
        binding.recycleviewOfcharacterslist.adapter= characterslistadapter

   setRecyclerViewScrollListener()

    }

    private fun searchCharacterbyName() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                characterviewModel.name.value=query.toString()
                characterviewModel.isFilter.value=true
                characterviewModel.listofFilteredItems.value!!.clear()
                characterviewModel.getBySpeciesAndStatusAndGenderAndName(query.toString(),
                    characterviewModel.filterValues.value!![0],
                    characterviewModel.filterValues.value!![1],
                    characterviewModel.filterValues.value!![2],
                    page)

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }
    private fun setRecyclerViewScrollListener() {
        binding.recycleviewOfcharacterslist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                characterviewModel.isPaging.value=true
                if(!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN))
                {
                    loading = true
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //for paging
                var firstVisibleItems: IntArray? =null
                totalItems=staggeredGridLayoutManager.itemCount
                visibleItems=staggeredGridLayoutManager.childCount
                firstVisibleItems=staggeredGridLayoutManager.findFirstVisibleItemPositions(firstVisibleItems)
                if(firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                    pastVisibleItem = firstVisibleItems[0];
                }
                if (loading && dy>0) {
                    if ((visibleItems+ pastVisibleItem) == totalItems) {
                        loading = false
                        page += 1
                        if (characterviewModel.isFilter.value==false){
                                characterviewModel.getCharacters(page)
                        }
                        else{
                          characterviewModel.getBySpeciesAndStatusAndGenderAndName(
                              characterviewModel.name.value.toString(),
                              characterviewModel.filterValues.value!![0],
                              characterviewModel.filterValues.value!![1],
                              characterviewModel.filterValues.value!![2],
                              page)
                        }



                    }
                }

            }
        })
    }

    private fun getLivedataObserve(){

            characterviewModel.liveDatalistofCharacter.observe(viewLifecycleOwner,{ response->
                if (response.isSuccessful){

                        characterviewModel.listofFilteredItems.value!!.addAll(response.body()!!.results)
                        characterslistadapter!!.setCharactersList(characterviewModel.listofFilteredItems.value!!)

                }
                else{
                    if (characterviewModel.isPaging.value==false){
                        characterslistadapter!!.setCharactersList(emptyList())
                    }

                }
            })


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

}