package n7.myperfectemptyproject.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.R
import n7.myperfectemptyproject.databinding.MainFragmentBinding
import n7.myperfectemptyproject.di.injector
import n7.myperfectemptyproject.ui.ErrorDialogDirections
import n7.myperfectemptyproject.utils.viewModelWithSavedStateHandle

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        const val MILLIS_FOR_EXIT = 2000L
    }

    private lateinit var binding: MainFragmentBinding
    private var finishActivity = false
    private val mainViewModel : MainViewModel by viewModelWithSavedStateHandle {
        injector.mainViewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner // without this all bindings in xml will no work!
            viewModel = mainViewModel
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupOnBackPressedAction()
        setupListAdapter()

        // mainViewModel.errorMessage.observe(viewLifecycleOwner) {
        //     if (it != null) showDialogWithError(it)
        // }
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
        mainViewModel.getUsers.observe(viewLifecycleOwner, usersListAdapter::submitList)
    }

    // handle back press action for this fragment
    private fun setupOnBackPressedAction() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (finishActivity) {
                requireActivity().finish()
            } else {
                finishActivity = true
                Snackbar.make(
                    binding.root,
                    getString(R.string.all_press_again_for_exit),
                    Snackbar.LENGTH_SHORT
                ).show()
                lifecycleScope.launch {
                    delay(MILLIS_FOR_EXIT)
                    finishActivity = false
                }
            }
        }
    }
}
