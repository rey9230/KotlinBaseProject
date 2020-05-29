package n7.myperfectemptyproject.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import n7.myperfectemptyproject.MainActivity
import n7.myperfectemptyproject.R
import n7.myperfectemptyproject.databinding.MainFragmentBinding
import n7.myperfectemptyproject.di.injector
import n7.myperfectemptyproject.ui.ErrorDialogListener
import n7.myperfectemptyproject.utils.extension.setOnBackPressExit
import n7.myperfectemptyproject.utils.extension.setupErrorSnackbar
import n7.myperfectemptyproject.utils.extension.showSnackbar
import n7.myperfectemptyproject.utils.viewModelWithSavedStateHandle

class MainFragment : Fragment(R.layout.main_fragment), ErrorDialogListener {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModelWithSavedStateHandle { injector.mainViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view).also {
            it.lifecycleOwner = viewLifecycleOwner // without this all bindings in xml will no work!
            it.viewModel = viewModel
        }

        setOnBackPressExit()
        setupListAdapter()
        setupErrorSnackbar()
        registerFragmentResultListener()

        binding.vToolbarTitle.setOnClickListener { (activity as? MainActivity)?.showDialogError("Dialog", this) }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun onResume() {
        super.onResume()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }

    override fun onPause() {
        super.onPause()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun registerFragmentResultListener() {
        parentFragmentManager.setFragmentResultListener(
            "key",
            viewLifecycleOwner,
            FragmentResultListener { _, result ->
                view?.showSnackbar(result.getString("key", "not hello"))
            })
    }

    private fun setupErrorSnackbar() {
        view?.setupErrorSnackbar(viewLifecycleOwner, viewModel.errorMessage)
    }

    private fun setupListAdapter() {
        val usersListAdapter = UsersListAdapter()
        val loadingAdapter = LoadingAdapter()
        val mergeAdapter = MergeAdapter(usersListAdapter, loadingAdapter)

        usersListAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rv.apply {
            setHasFixedSize(true)
            adapter = mergeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            postponeEnterTransition() // exitTransition animation with this works as intended
            viewTreeObserver.addOnPreDrawListener { startPostponedEnterTransition(); true }
        }

        viewModel.getUsers.observe(viewLifecycleOwner, usersListAdapter::submitList)
        viewModel.isLoading.observe(viewLifecycleOwner, loadingAdapter::isLoading)
    }

    override fun onPositiveButtonClick() {
        view?.showSnackbar("ok")
    }
}
