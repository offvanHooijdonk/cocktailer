package by.off.cocktailer.ui.cocktail.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import by.off.cocktailer.R
import by.off.cocktailer.databinding.CocktailsListBinding
import by.off.cocktailer.ui.setupTheme
import kotlinx.android.synthetic.main.fr_cocktails_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CocktailListFragment : Fragment() {
    private val viewModel: CocktailListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<CocktailsListBinding>(layoutInflater, R.layout.fr_cocktails_list, container, false).let {
            it.model = viewModel
            it.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_cocktails.adapter = CocktailAdapter()

        refresh_layout.setupTheme()
        refresh_layout.setOnRefreshListener {
            viewModel.reloadData()
        }
    }
}
