package uz.quvonchbek.myonlineshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.*
import uz.quvonchbek.myonlineshop.adapters.AdapterKatalog
import uz.quvonchbek.myonlineshop.adapters.OnclickKatalog
import uz.quvonchbek.myonlineshop.databinding.FragmentKatalogBinding
import uz.quvonchbek.myonlineshop.models.Katalog


class KatalogFragment : Fragment() {

    lateinit var binding:FragmentKatalogBinding
    lateinit var typeList:ArrayList<Katalog>
    lateinit var adapterKatalog: AdapterKatalog
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentKatalogBinding.inflate(layoutInflater, container, false)
        loadData()

        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("product")



        adapterKatalog= AdapterKatalog(typeList,object :OnclickKatalog{
            override fun onclickItem(typename: String) {
               var bundle=Bundle()
                bundle.putString("key_type",typename)
                findNavController().navigate(R.id.typeProductFragment,bundle)
            }

        })
        binding.rvKatolog.adapter=adapterKatalog

        return binding.root
    }

    private fun loadData() {


        typeList= ArrayList()
        typeList.add(
            Katalog("Telefon va Smartfonlar","https://firebasestorage.googleapis.com/v0/b/myfirebase-8f3d5.appspot.com/o/imagesS21.jpg?alt=media&token=2830ebbb-1bb8-463e-bcfd-0990d7e3dba3") )
        typeList.add(
            Katalog("Noutbook va Kompyuter","https://firebasestorage.googleapis.com/v0/b/myfirebase-8f3d5.appspot.com/o/noutbook.jpg?alt=media&token=9e674cb6-47d1-40a1-a451-636a13a322b4")   )
        typeList.add(Katalog("Televizor va monitorlar","https://firebasestorage.googleapis.com/v0/b/myfirebase-8f3d5.appspot.com/o/televizor.jpg?alt=media&token=074c2285-1b76-4fc2-9e0e-5c2b41f771ee"))
        typeList.add(Katalog("Maishiy texnika","https://firebasestorage.googleapis.com/v0/b/myfirebase-8f3d5.appspot.com/o/mtex.jpg?alt=media&token=3292b2cc-b228-4e67-971b-8f0c18067ebe"))
        typeList.add(
            Katalog("Oshxona uchun texnika","https://firebasestorage.googleapis.com/v0/b/myfirebase-8f3d5.appspot.com/o/plita.jpg?alt=media&token=ce68b05a-8d77-4f1f-b12d-c1cc1f4e43aa"))


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KatalogFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}