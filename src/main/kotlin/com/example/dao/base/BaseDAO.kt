package com.example.dao.base

import org.ktorm.database.Database
import org.ktorm.dsl.QuerySource
import org.ktorm.dsl.from
import org.ktorm.entity.*
import org.ktorm.schema.ColumnDeclaring
import org.ktorm.schema.Table
import javax.annotation.Resource

abstract class BaseDAO<E : Entity<E>, T : Table<E>>(private val tableObject: T) {
    @Resource
    protected lateinit var database: Database

    open fun add(entity: E): Int {
        return database.sequenceOf(tableObject).add(entity)
    }

    open fun update(entity: E): Int {
        return database.sequenceOf(tableObject).update(entity)
    }

    open fun deleteIf(predicate: (T) -> ColumnDeclaring<Boolean>): Int {
        return database.sequenceOf(tableObject).removeIf(predicate)
    }

    open fun allMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return database.sequenceOf(tableObject).all(predicate)
    }

    open fun anyMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return database.sequenceOf(tableObject).any(predicate)
    }

    open fun noneMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return database.sequenceOf(tableObject).none(predicate)
    }

    open fun count(): Int {
        return database.sequenceOf(tableObject).count()
    }

    open fun count(predicate: (T) -> ColumnDeclaring<Boolean>): Int {
        return database.sequenceOf(tableObject).count(predicate)
    }

    open fun findOne(predicate: (T) -> ColumnDeclaring<Boolean>): E? {
        return database.sequenceOf(tableObject).find(predicate)
    }

    open fun findList(predicate: (T) -> ColumnDeclaring<Boolean>): List<E> {
        return database.sequenceOf(tableObject).filter(predicate).toList()
    }

    open fun findAll(): List<E> {
        return database.sequenceOf(tableObject).toList()
    }

    open fun getDSL(): QuerySource {
        return database.from(tableObject)
    }

    open fun getSequence(): EntitySequence<E, T> {
        return database.sequenceOf(tableObject)
    }

}
