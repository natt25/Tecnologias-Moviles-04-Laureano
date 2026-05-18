package com.natty.e04.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertar(producto: Producto): Long

    @Query("SELECT * FROM productos WHERE codigo = :codigo LIMIT 1")
    suspend fun buscarPorCodigo(codigo: Int): Producto?

    @Update
    suspend fun actualizar(producto: Producto): Int

    @Query("DELETE FROM productos WHERE codigo = :codigo")
    suspend fun eliminarPorCodigo(codigo: Int): Int

    @Query("SELECT * FROM productos ORDER BY codigo ASC")
    fun listarTodos(): Flow<List<Producto>>
}