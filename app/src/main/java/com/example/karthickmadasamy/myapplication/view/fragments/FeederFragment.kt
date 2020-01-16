package com.example.karthickmadasamy.myapplication.view.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.karthickmadasamy.myapplication.R
import com.example.karthickmadasamy.myapplication.adapters.FeederAdapter
import com.example.karthickmadasamy.myapplication.models.FeederModel
import com.example.karthickmadasamy.myapplication.models.Rows
import com.example.karthickmadasamy.myapplication.presenter.MainPresenter
import com.example.karthickmadasamy.myapplication.presenter.MainViewInterface
import com.example.karthickmadasamy.myapplication.viewmodel.FeederViewModel

import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.feeder_fragment.*
//Added SwipeRefresh action to this class
//import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView




/**
 * Its our main fragment which renders UI
 * Butterknife is a view binding tool that uses annotations to generate boilerplate code for us
 * Used MVP Design pattern in our project
 * MVP pattern allows separating the presentation layer from the logic so that everything about how the UI works is agnostic from how we represent it on screen
 * Created by Karthick.Madasamy on 12/4/2019.
 */

class FeederFragment : BaseFragment(), MainViewInterface {
    private val TAG = this@FeederFragment.javaClass.name


    @BindView(R.id.feeder_view)
    lateinit var newsView: RecyclerView

    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

   /* @BindView(R.id.swipeToRefresh)
    internal var mSwipeRefreshLayout: SwipeRefreshLayout? = null*/

    var adapter: FeederAdapter? = null
    internal lateinit var mainPresenter: MainPresenter
    private val mRowsList: List<Rows>? = null

    private var feederViewModel: FeederViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FeederAdapter(this.context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.feeder_fragment, container, false)
        ButterKnife.bind(this, view)
        feederViewModel = ViewModelProviders.of(this).get(FeederViewModel::class.java)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        newsView!!.layoutManager = LinearLayoutManager(activity)
        newsView!!.adapter = adapter

        initMVP()

        swipeRefresh()
        return view
    }

    /**
     * initialize Model View Presenter
     */
    private fun initMVP() {
        mainPresenter = MainPresenter(this, this!!.activity!!)
        if (isNetworkAvailable) {
            mainPresenter.getRows()
        }
    }

    private fun swipeRefresh() {
     /*   mSwipeRefreshLayout!!.setColorSchemeResources(R.color.colorAccent)
        mSwipeRefreshLayout!!.setOnRefreshListener {
            mainPresenter.getRows()
            mSwipeRefreshLayout!!.isRefreshing = false
        }*/
    }

    override fun showToast(str: String) {
        Toast.makeText(activity, str, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar!!.visibility = View.GONE
    }

    override fun displayFeeder(newsResponse: FeederModel) {

    }


    override fun displayError(e: String) {
        if (adapter != null && adapter!!.itemCount > 0)
            errorDialog(R.string.server_error_message, false)
        else
            errorDialog(R.string.server_error_message, true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu!!.clear()
        inflater!!.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        if (id == R.id.refresh) {
            mainPresenter.getRows()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        private val FEEDER_LIST = "FeederEntity Adapter Data"

        fun newInstance(): FeederFragment {
            return FeederFragment()
        }
    }
}

