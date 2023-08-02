/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.android.code_check.MainActivity.Companion.lastSearchDate
import jp.co.yumemi.android.code_check.databinding.FragmentRepositoryBinding

@AndroidEntryPoint
class RepositoryFragment : Fragment(R.layout.fragment_repository) {

    private val args: RepositoryFragmentArgs by navArgs()

    private var binding: FragmentRepositoryBinding? = null
    private val _binding get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("検索した日時", lastSearchDate.toString())

        binding = FragmentRepositoryBinding.bind(view)

        val item = args.item

        _binding.ownerIconView.load(item.ownerIconUrl)
        _binding.nameView.text = item.name
        _binding.languageView.text = getString(R.string.written_language, item.language)
        _binding.starsView.text = getString(R.string.stars_view, item.stargazersCount)
        _binding.watchersView.text = getString(R.string.watchers_view, item.watchersCount)
        _binding.forksView.text = getString(R.string.forks_view, item.forksCount)
        _binding.openIssuesView.text = getString(R.string.open_issues_view, item.openIssuesCount)
    }
}
