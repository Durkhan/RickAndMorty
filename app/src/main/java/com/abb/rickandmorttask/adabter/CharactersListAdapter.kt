package com.abb.rickandmorttask.adabter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.abb.rickandmorttask.databinding.AdapterOfcharacterBinding
import com.abb.rickandmorttask.fragments.CharactersListFragmentDirections
import com.abb.rickandmorttask.model.Character
import com.bumptech.glide.Glide


class CharactersListAdapter(val context: Context):RecyclerView.Adapter<CharactersListAdapter.CharacterViewHolder>() {
    private var listofChracacters= emptyList<Character>()


    fun setCharactersList(characters: List<Character>) {
        listofChracacters = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersListAdapter.CharacterViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=AdapterOfcharacterBinding.inflate(inflater,parent,false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersListAdapter.CharacterViewHolder, position: Int) {
             with(holder.binding){
                  characterName.text=listofChracacters[position].name
                  characterSpeciesAndgender.text=(listofChracacters[position].species+"•"+listofChracacters[position].gender)
                  characterStatus.text=listofChracacters[position].status
                  Glide.with(context).load(listofChracacters[position].image).into(characterImg)

             }
        holder.itemView.setOnClickListener{view->
            val action=CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailsFragment(listofChracacters[position])
            view.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
       return listofChracacters.size
    }

    class CharacterViewHolder(val binding: AdapterOfcharacterBinding):RecyclerView.ViewHolder(binding.root)



}