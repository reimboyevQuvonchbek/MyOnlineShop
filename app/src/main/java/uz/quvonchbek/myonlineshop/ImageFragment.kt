package uz.quvonchbek.myonlineshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import uz.quvonchbek.myonlineshop.databinding.FragmentImageBinding
import java.lang.Exception

private const val ARG_PARAM1 = "param1"

class ImageFragment : Fragment() {
    private var param1: String? = null
    private lateinit var binding: FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentImageBinding.inflate(layoutInflater)

        Picasso.get().load(param1).placeholder(R.drawable.image)
            .into(binding.image, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    binding.image.visibility = View.VISIBLE
                    binding.progressCircular.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {

                }

            })

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}