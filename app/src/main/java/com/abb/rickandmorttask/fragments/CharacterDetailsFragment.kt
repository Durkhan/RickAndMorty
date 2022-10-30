package com.abb.rickandmorttask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.abb.rickandmorttask.databinding.DetailsOfcharactersBinding
import com.bumptech.glide.Glide

class CharacterDetailsFragment: Fragment() {
    private  var _binding: DetailsOfcharactersBinding?=null
    private val binding get()=_binding!!
    private val args:CharacterDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsOfcharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character=args.characterdetails
        with(binding){
            Glide.with(requireContext()).load(character.image).into(imgCharacter)
            characterName.text=character.name
            characterStatus.text=character.status
            characterGender.text=character.gender
            characterOrigin.text=character.origin.name
            characterSpecie.text=character.species
            characterEpisodes.text=character.episode.size.toString()
            characterLocation.text=character.location.name
            charaterType.text=character.type
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}