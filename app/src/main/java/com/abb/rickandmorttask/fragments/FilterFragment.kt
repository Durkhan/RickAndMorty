package com.abb.rickandmorttask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.abb.rickandmorttask.R
import com.abb.rickandmorttask.databinding.FragmentFilterofcharactersBinding
import com.abb.rickandmorttask.extension.*
import com.abb.rickandmorttask.retrofit.Repository
import com.abb.rickandmorttask.viewmodel.CharacterListViewModel
import com.abb.rickandmorttask.viewmodel.CharactersViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment:BottomSheetDialogFragment() {

    private val viewModel:CharacterListViewModel by activityViewModels{ CharactersViewModelFactory(
        Repository()
    )}

    private var _binding:FragmentFilterofcharactersBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentFilterofcharactersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.filterValue.observe(viewLifecycleOwner, {
            with(binding) {
                chipgroupSpecies.setSpeciesChipChecked(it[0])
                chipgroupStatus.setStatusChipChecked(it[1])
                radiogroupGender.setButtonChecked(it[2])
            }
        })
        with(binding) {
            btnMakeFilter.setOnClickListener {
                viewModel.listofFilteredItems.value!!.clear()
                viewModel.name.value=" "
                if (chipgroupSpecies.getStatusTextChipChecked()
                        .isNotEmpty() && chipgroupStatus.getStatusTextChipChecked()
                        .isNotEmpty() && radiogroupGender.getTextButtonChecked().isNotEmpty()
                ) {
                    viewModel.getBySpeciesAndStatusAndGenderAndName(
                        viewModel.name.value.toString(),
                        chipgroupSpecies.getSpeciesTextChipChecked(),
                        chipgroupStatus.getStatusTextChipChecked(),
                        radiogroupGender.getTextButtonChecked(),
                        1
                    )



                    viewModel.filterValue.value =
                        arrayOf(chipgroupSpecies.checkedChipId,chipgroupStatus.checkedChipId, radiogroupGender.checkedRadioButtonId)

                    viewModel.filterValues.value =
                        arrayOf(
                            viewModel.name.value.toString(),
                            chipgroupSpecies.getSpeciesTextChipChecked(),
                            chipgroupStatus.getStatusTextChipChecked(),
                            radiogroupGender.getTextButtonChecked()
                        )


                }

                    findNavController().popBackStack(R.id.charactersListFragment, false)

            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}