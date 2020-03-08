package n7.myperfectemptyproject.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

/**
 * old way for checking network state is deprecated
 * {@link https://developer.android.com/training/monitoring-device-state/connectivity-status-type }
 * so we use new approach with liveData
 *
 * Here is an example
 * <pre>
 *
 *  class Fragment : Fragment() {
 *
 *      override fun onActivityCreated(savedInstanceState: Bundle?) {
 *      ...
 *           NetworkEvents.distinctUntilChanged()
 *              .observe(viewLifecycleOwner) { handleConnectivityChange(it.networkState) }
 *      ...
 *      }
 *
 *      private fun handleConnectivityChange(networkState: NetworkState) {
 *          if (networkState.isConnected) {
 *                    binding.tvNetworkStatus.text = " ON "
 *                }
 *          if (!networkState.isConnected) {
 *                    binding.tvNetworkStatus.text = " OFF "
 *                }
 *
 *      }
 * </pre>
 */
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
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            NetworkCallbackImp(holder)
        )
    }
}

internal class NetworkStateImpl : NetworkState {
    override var network: Network? = null

    override var isConnected: Boolean = false
        set(value) {
            field = value
            NetworkEvents.notify(if (value) NetworkEvent.ConnectivityAvailable else NetworkEvent.ConnectivityLost)
        }
    override var linkProperties: LinkProperties? = null
        set(value) {
            val event = NetworkEvent.LinkPropertyChanged(field)
            field = value
            NetworkEvents.notify(event)
        }

    override var networkCapabilities: NetworkCapabilities? = null
        set(value) {
            val event = NetworkEvent.NetworkCapabilityChanged(field)
            field = value
            NetworkEvents.notify(event)
        }
}

internal class NetworkCallbackImp(private val holder: NetworkStateImpl) :
    ConnectivityManager.NetworkCallback() {
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

sealed class NetworkEvent {

    val networkState: NetworkState = NetworkStateHolder

    object ConnectivityLost : NetworkEvent()
    object ConnectivityAvailable : NetworkEvent()
    data class NetworkCapabilityChanged(val old: NetworkCapabilities?) : NetworkEvent()
    data class LinkPropertyChanged(val old: LinkProperties?) : NetworkEvent()
}

object NetworkEvents : LiveData<NetworkEvent>() {
    internal fun notify(event: NetworkEvent) {
        postValue(event)
    }
}
