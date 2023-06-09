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
import com.macarenarodriguezboleto.openeyes.features.googleMap.MapsActivity

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

        // Se inicializa el servicio de Firebase autentificacion
        firebaseAuth = FirebaseAuth.getInstance()

        // Se revisa que el usuario no siga logeado en cuyo caso se muestra correctamente la vista correspondiente
        firebaseAuth.currentUser?.reload()

        binding.btnCreateUser.setOnClickListener {
            if (binding.edtPassword1.length() >= 6 && binding.edtEmail1.length() >= 6) {
                createUser(
                    binding.edtEmail1.text.toString(),
                    binding.edtPassword1.text.toString(),
                    view
                )
            } else {
                //Si la creacion no es correcta se muestra un mensaje de error
                showAlert("Debe rellenar los campos correctamente")
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
                //Si el login no es correcto se muestra un mensaje de error en el login
                showAlert("Debe rellenar los campos correctamente")
            }
        })
    }

    private fun createUser(email: String?, password: String?, view: View) {
        firebaseAuth.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Si la creacion es correcta se loguea automaticamente y pasa a la siguiente pantalla
                    val user: FirebaseUser = firebaseAuth.currentUser!!
                    val intent = Intent(activity, MapsActivity::class.java)
                    startActivity(intent)
                } else {
                    // Si falla la autenticación se muestra un mensaje de error
                    showAlert("A ocurrido un fallo en la creación del usuario o el usuario ya está creado")
                }
            }
    }

    private fun signIn(email: String?, password: String?, view: View) {
        firebaseAuth.signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user: FirebaseUser = firebaseAuth.currentUser!!
                    val intent = Intent(activity, MapsActivity::class.java)
                    startActivity(intent)
                } else {
                    // Si falla la autenticación se muestra un mensaje de error
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