package com.score.pro.view.fragments.authFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import score.pro.R
import com.score.pro.utils.SpewViewModel
import com.score.pro.utils.Status

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [LoginFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var communicator:AuthFragmentCommunicationListener?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm=SpewViewModel.giveMeViewModelAuth(requireActivity())
        view.findViewById<View>(R.id.sign_up).setOnClickListener {
            communicator?.onMessageFromFragment(this,FragmentMessage.CHANGE_TO_SIGN_UP)
        }
        vm.loginLiveDataListener.observe(requireActivity()){
            when(it.status) {
                Status.SUCCESS -> {
                    communicator?.onMessageFromFragment(
                        this@LoginFrag,
                        FragmentMessage.LOGIN_SUCCESS
                    )
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        }

    }
    fun setFragmentCommunicator(communicationListener: AuthFragmentCommunicationListener){
        communicator=communicationListener
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}