package com.natty.e04.data

import kotlinx.coroutines.flow.Flow

class ProductoRepository(private val productoDao: ProductoDao) {

    fun listarTodos(): Flow<List<Producto>> {
        return productoDao.listarTodos()
    }

    suspend fun insertar(producto: Producto): Long {
        return productoDao.insertar(producto)
    }

    suspend fun buscarPorCodigo(codigo: Int): Producto? {
        return productoDao.buscarPorCodigo(codigo)
    }

    suspend fun actualizar(producto: Producto): Int {
        return productoDao.actualizar(producto)
    }

    suspend fun eliminarPorCodigo(codigo: Int): Int {
        return productoDao.eliminarPorCodigo(codigo)
    }
}