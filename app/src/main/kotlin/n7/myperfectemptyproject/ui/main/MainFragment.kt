package n7.myperfectemptyproject.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.R
import n7.myperfectemptyproject.databinding.MainFragmentBinding
import n7.myperfectemptyproject.di.injector
import n7.myperfectemptyproject.ui.ErrorDialogDirections
import n7.myperfectemptyproject.utils.setOnBackPressExit
import n7.myperfectemptyproject.utils.showSnackbar
import n7.myperfectemptyproject.utils.viewModelWithSavedStateHandle

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModelWithSavedStateHandle { injector.mainViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view).also {
            it.lifecycleOwner = viewLifecycleOwner // without this all bindings in xml will no work!
            it.viewModel = viewModel
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnBackPressExit()
        setupListAdapter()
        setupErrorMessage()
    }

    private fun setupErrorMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { showDialogWithError(it) }
        }
    }

    private fun setupSnackbar() {
        // view?.setupSnackbar(this, mainViewModel.errorMessage)
    }

    // show dialog from navGraph
    private fun showDialogWithError(message: String) =
        findNavController().navigate(ErrorDialogDirections.actionGlobalErrorDialog(message))

    private fun setupListAdapter() {
        val usersListAdapter = UsersListAdapter()
        binding.rv.apply {
            setHasFixedSize(true)
            adapter = usersListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            postponeEnterTransition() // exitTransition animation with this works as intended
            viewTreeObserver.addOnPreDrawListener { startPostponedEnterTransition(); true }
        }
        viewModel.getUsers.observe(viewLifecycleOwner, usersListAdapter::submitList)
    }
}
