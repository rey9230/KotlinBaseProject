package n7.myperfectemptyproject.utils

import android.app.Application
import android.content.Context
import android.net.*
import androidx.lifecycle.LiveData

// old way for checking network state is depricated
// https://developer.android.com/training/monitoring-device-state/connectivity-status-type
// so we use new approach with liveData
object NetworkStateHolder : NetworkState {

    private lateinit var holder: NetworkStateImpl

    override val isConnected: Boolean
        get() = holder.isConnected
    override val network: Network?
        get() = holder.network
    override val networkCapabilities: NetworkCapabilities?
        get() = holder.networkCapabilities
    override val linkProperties: LinkProperties?
        get() = holder.linkProperties

    fun Application.registerConnectivityMonitor() {
        holder = NetworkStateImpl()
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), NetworkCallbackImp(holder))
    }
}

internal class NetworkStateImpl : NetworkState {
    override var network: Network? = null

    override var isConnected: Boolean = false
        set(value) {
            field = value
            NetworkEvents.notify(if (value) Event.ConnectivityAvailable else Event.ConnectivityLost)
        }
    override var linkProperties: LinkProperties? = null
        set(value) {
            val event = Event.LinkPropertyChanged(field)
            field = value
            NetworkEvents.notify(event)
        }

    override var networkCapabilities: NetworkCapabilities? = null
        set(value) {
            val event = Event.NetworkCapabilityChanged(field)
            field = value
            NetworkEvents.notify(event)
        }
}

internal class NetworkCallbackImp(private val holder: NetworkStateImpl) : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
        holder.network = network
        holder.isConnected = true
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        holder.networkCapabilities = networkCapabilities
    }

    override fun onLost(network: Network) {
        holder.network = network
        holder.isConnected = false
    }

    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        holder.linkProperties = linkProperties
    }
}

interface NetworkState {
    val isConnected: Boolean
    val network: Network?
    val networkCapabilities: NetworkCapabilities?
    val linkProperties: LinkProperties?
}

sealed class Event {

    val networkState: NetworkState = NetworkStateHolder

    object ConnectivityLost : Event()
    object ConnectivityAvailable : Event()
    data class NetworkCapabilityChanged(val old: NetworkCapabilities?) : Event()
    data class LinkPropertyChanged(val old: LinkProperties?) : Event()
}

object NetworkEvents : LiveData<Event>() {
    internal fun notify(event: Event) {
        postValue(event)
    }
}
