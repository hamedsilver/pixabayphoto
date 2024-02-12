package com.example.pixabayphoto.mockData

import com.example.pixabayphoto.data.local.dao.SearchSuggestionDao
import com.example.pixabayphoto.data.local.model.SearchSuggestionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MockSearchSuggestionDao: SearchSuggestionDao {

    private val mockDB = MutableStateFlow(emptyList<SearchSuggestionEntity>())

    override fun insertSearchTerms(vararg terms: SearchSuggestionEntity) {
        mockDB.value = terms.toList()
    }

    override fun loadAllSearchTerms(): Flow<List<SearchSuggestionEntity>> = mockDB
}
