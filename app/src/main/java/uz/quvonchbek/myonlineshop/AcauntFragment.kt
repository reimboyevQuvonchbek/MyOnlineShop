package uz.quvonchbek.myonlineshop

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import uz.quvonchbek.myonlineshop.databinding.FragmentAcauntBinding
import uz.quvonchbek.myonlineshop.utils.MyShared


class AcauntFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    lateinit var binding: FragmentAcauntBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAcauntBinding.inflate(layoutInflater, container, false)
        var user=MainActivity.getUserA(requireContext())
        binding.phonenumber.setText(user.phoneNumber)
        binding.username.setText(user.name)
        binding.relative5.setOnClickListener{
            val myShared1: MyShared = MyShared.getInstance(requireContext())
            myShared1.activeUser = null

            var intent=Intent(requireActivity(),LoginActivity::class.java)
            startActivity(intent)
            (requireActivity() as MainActivity).finish()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AcauntFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}