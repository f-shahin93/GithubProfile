package com.shahin.githubprofile.ui.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.viewModels
import com.shahin.githubprofile.R
import com.shahin.githubprofile.databinding.FragmentProfileBinding
import com.shahin.githubprofile.di.ViewModelFactory
import com.shahin.githubprofile.ui.MainActivity
import com.shahin.githubprofile.ui.base.BaseFragment
import com.shahin.githubprofile.ui.utils.setSpannableText
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.profile.observe(viewLifecycleOwner) {
            binding.run {
                imgUrl = it.avatarUrl
                name = it.login
                it.url?.let { url -> setSpannableUrl(url) }
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message ?: "Error Occur", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSpannableUrl(url: String) {
        requireContext().setSpannableText(
            text = url,
            textView = binding.tvUrl,
            onClick = {
                CustomTabsIntent.Builder().build().launchUrl(requireContext(), Uri.parse(url))
            },
            isUnderlineText = true,
            startIndex = 0,
            endIndex = url.length
        )
    }

}