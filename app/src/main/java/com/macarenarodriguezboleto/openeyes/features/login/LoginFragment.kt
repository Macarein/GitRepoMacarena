package com.macarenarodriguezboleto.openeyes.features.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.macarenarodriguezboleto.openeyes.R
import com.macarenarodriguezboleto.openeyes.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {}
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if user is signed in (non-null) and update UI accordingly.
        firebaseAuth.currentUser?.reload()

        binding.btnCreateUser.setOnClickListener {
            if (binding.edtPassword1.length() >= 6 && binding.edtEmail1.length() >= 6) {
                createUser(
                    binding.edtEmail1.text.toString(),
                    binding.edtPassword1.text.toString(),
                    view
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Debe rellenar los campos correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            if (binding.edtPassword1.length() >= 6 && binding.edtEmail1.length() >= 6) {
                signIn(
                    binding.edtEmail1.text.toString(),
                    binding.edtPassword1.text.toString(),
                    view
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Debe rellenar los campos correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun createUser(email: String?, password: String?, view: View) {
        firebaseAuth.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Create OK", "createUserWithEmail:success")
                    val user: FirebaseUser = firebaseAuth.currentUser!!
                    findNavController().navigate(R.id.action_loginFragment_to_googleMapFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    showAlert("El usuario ya está creado")
                }
            }
    }

    private fun signIn(email: String?, password: String?, view: View) {
        firebaseAuth.signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user: FirebaseUser = firebaseAuth.currentUser!!
                    findNavController().navigate(R.id.action_loginFragment_to_googleMapFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    showAlert("Fallo en la autenticación")
                }
            }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}