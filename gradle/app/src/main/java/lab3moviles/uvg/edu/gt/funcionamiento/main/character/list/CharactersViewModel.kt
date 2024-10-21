package lab3moviles.uvg.edu.gt.funcionamiento.main.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.data.data.DataStore.DataSyncManager

class CharactersViewModel(
    private val dataSyncManager: DataSyncManager
) : ViewModel() {

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    init {
        syncData()
    }

    private fun syncData() {
        viewModelScope.launch {
            _isSyncing.value = true
            dataSyncManager.syncData()
            _isSyncing.value = false
        }
    }
}
