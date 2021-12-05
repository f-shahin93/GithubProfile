package com.shahin.githubprofile.ui.repolist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shahin.githubprofile.R
import com.shahin.githubprofile.databinding.FragmentRepositoryListBinding
import com.shahin.githubprofile.di.ViewModelFactory
import com.shahin.githubprofile.ui.MainActivity
import com.shahin.githubprofile.ui.base.BaseFragment
import javax.inject.Inject


class RepositoryListFragment :
    BaseFragment<FragmentRepositoryListBinding>(R.layout.fragment_repository_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<RepoListViewModel> { viewModelFactory }

    private lateinit var adapter: RepoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        adapter = RepoAdapter(viewLifecycleOwner)
        binding.list.run {
            adapter = this@RepositoryListFragment.adapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            setupProgressBar(it.isNullOrEmpty())
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            setupProgressBar(it && adapter.currentList.isNullOrEmpty())
        }
    }

    private fun setupProgressBar(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }


}