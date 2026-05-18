package com.natty.e02

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.natty.e02.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dao: ProductoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = AppDatabase.getInstance(this).productoDao()

        binding.btnRegistrar.setOnClickListener { registrar() }
        binding.btnBuscar.setOnClickListener { buscar() }
        binding.btnModificar.setOnClickListener { modificar() }
        binding.btnEliminar.setOnClickListener { eliminar() }
    }

    private fun registrar() {
        val codigo = binding.txtCodigo.text.toString()
        val nombre = binding.txtNombre.text.toString()
        val categoria = binding.txtCategoria.text.toString()
        val talla = binding.txtTalla.text.toString()
        val precio = binding.txtPrecio.text.toString()

        if (codigo.isEmpty() || nombre.isEmpty() || categoria.isEmpty() || talla.isEmpty() || precio.isEmpty()) {
            toast("Debe llenar todos los campos")
            return
        }

        val producto = Producto(
            codigo = codigo.toInt(),
            nombre = nombre,
            categoria = categoria,
            talla = talla,
            precio = precio.toDouble()
        )

        lifecycleScope.launch {
            try {
                dao.insertar(producto)
                limpiarCampos()
                toast("Producto registrado correctamente")
            } catch (e: Exception) {
                toast("Error al registrar: el código ya existe")
            }
        }
    }

    private fun buscar() {
        val codigo = binding.txtCodigo.text.toString()

        if (codigo.isEmpty()) {
            toast("Debe ingresar el código del producto")
            return
        }

        lifecycleScope.launch {
            val producto = dao.buscarPorCodigo(codigo.toInt())

            if (producto != null) {
                binding.txtNombre.setText(producto.nombre)
                binding.txtCategoria.setText(producto.categoria)
                binding.txtTalla.setText(producto.talla)
                binding.txtPrecio.setText(producto.precio.toString())
                toast("Producto encontrado")
            } else {
                toast("No existe el producto")
            }
        }
    }

    private fun modificar() {
        val codigo = binding.txtCodigo.text.toString()
        val nombre = binding.txtNombre.text.toString()
        val categoria = binding.txtCategoria.text.toString()
        val talla = binding.txtTalla.text.toString()
        val precio = binding.txtPrecio.text.toString()

        if (codigo.isEmpty() || nombre.isEmpty() || categoria.isEmpty() || talla.isEmpty() || precio.isEmpty()) {
            toast("Debe llenar todos los campos")
            return
        }

        val producto = Producto(
            codigo = codigo.toInt(),
            nombre = nombre,
            categoria = categoria,
            talla = talla,
            precio = precio.toDouble()
        )

        lifecycleScope.launch {
            val filasActualizadas = dao.actualizar(producto)

            if (filasActualizadas == 1) {
                toast("Producto modificado correctamente")
                limpiarCampos()
            } else {
                toast("El producto no existe")
            }
        }
    }

    private fun eliminar() {
        val codigo = binding.txtCodigo.text.toString()

        if (codigo.isEmpty()) {
            toast("Debe ingresar el código del producto")
            return
        }

        lifecycleScope.launch {
            val filasEliminadas = dao.eliminarPorCodigo(codigo.toInt())

            if (filasEliminadas == 1) {
                toast("Producto eliminado correctamente")
                limpiarCampos()
            } else {
                toast("El producto no existe")
            }
        }
    }

    private fun limpiarCampos() {
        binding.txtCodigo.setText("")
        binding.txtNombre.setText("")
        binding.txtCategoria.setText("")
        binding.txtTalla.setText("")
        binding.txtPrecio.setText("")
    }

    private fun toast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
