import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast
import com.azamovhudstc.bookappwithcache.repo.impl.BookRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// AirplaneModeChangeReceiver class extending BroadcastReceiver class
class NetworkReceiver : BroadcastReceiver() {

    companion object{
        lateinit var networkReceiver: NetworkReceiver
        fun getInstance(): NetworkReceiver
        {
            if(!::networkReceiver.isInitialized){
                networkReceiver=NetworkReceiver()
            }

            return  NetworkReceiver()
        }

    }


    private lateinit var connectListener:(()->Unit)

    fun setOnConnectListener(f :(()->Unit)){
        connectListener=f
    }

    // this function will be executed when the user changes his
    // airplane mode
    override fun onReceive(context: Context?, intent: Intent?) {
        connectListener.invoke()
    }
}
