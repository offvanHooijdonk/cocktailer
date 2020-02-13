package by.off.cocktailer.ui.cocktail.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.off.cocktailer.R
import by.off.cocktailer.databinding.ItemCocktailBinding
import by.off.cocktailer.model.CocktailModel

class CocktailAdapter : RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {
    private val list = mutableListOf<CocktailModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_cocktail, parent, false),
            ItemCocktailViewModel()
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(newData: List<CocktailModel>) {
        list.apply { clear(); addAll(newData) }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCocktailBinding, private val viewModel: ItemCocktailViewModel) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CocktailModel) {
            viewModel.cocktail.set(model)
            binding.model = viewModel
        }
    }
}