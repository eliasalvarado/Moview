package com.example.moview.Fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Fragments.Adapters.Home.adapter.CategoryRecyclerAdapter
import com.example.moview.Fragments.Adapters.News.adapter.NewsAdapter
import com.example.moview.Fragments.Adapters.News.model.NewsClass
import com.example.moview.MainActivity
import com.example.moview.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class NewsFragment : Fragment(R.layout.fragment_news), NewsAdapter.RecyclerViewNewsClickHandler {
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var listNews: MutableList<NewsClass>
    private lateinit var newsRecyclerAdapter: NewsAdapter
    private lateinit var newsRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        instanceData()
        setListeners()
        setRecycler(listNews)
    }

    private fun setRecycler(news: MutableList<NewsClass>) {
        newsRecyclerView = requireView().findViewById(R.id.news_recycler)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        newsRecyclerView!!.layoutManager = layoutManager
        newsRecyclerAdapter = NewsAdapter(requireContext(), news, this)
        newsRecyclerView!!.adapter = newsRecyclerAdapter

    }

    private fun instanceData() {
        val list: MutableList<NewsClass> = ArrayList()
        list.add(NewsClass("Breaking Bad", "12/02/02", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."))
        list.add(NewsClass("No Half Measures: Creating the Final Season of Breaking Bad", "12/02/02", "https://cdn.watchmode.com/posters/01672293_poster_w185.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."))
        listNews = list
    }

    private fun setListeners(){
        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.search -> requireView().findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToSearchFragment())
                R.id.home -> requireView().findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToHomeFragment())
                R.id.profile -> requireView().findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToProfileFragment())
            }
            true
        }
    }

    override fun onNewsClicked(news: NewsClass) {
        println("Se ha presionado el item "+ news.toString())
    }


}