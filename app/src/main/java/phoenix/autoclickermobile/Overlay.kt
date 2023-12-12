package phoenix.autoclickermobile

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.Button

val TAG_NAME = "Overlay"

class Overlay : Service(), OnTouchListener, OnClickListener {

    private lateinit var windowManager: WindowManager
    private lateinit var drawer: View
    private var initialX = 0
    private var initialY = 0
    private var initialTouchX = 0.0f
    private var initialTouchY = 0.0f
    private var moving = false
    private lateinit var instrumentsPanel: View
    private lateinit var instrumentsParams: WindowManager.LayoutParams

    private var instrumentsPanelVisible = false

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        drawer = layoutInflater.inflate(R.layout.drawer, null)
        drawer.setOnTouchListener(this)
        val drawerParams = Utils.getLayoutParams()
        drawerParams.gravity = Gravity.RIGHT or Gravity.TOP
        drawerParams.x = 0
        drawerParams.y = 40
        windowManager.addView(drawer, drawerParams)

        instrumentsPanel = layoutInflater.inflate(R.layout.instruments_panel, null)
        instrumentsPanel.findViewById<Button>(R.id.stopService).setOnClickListener {
            stopSelf()
        }
        instrumentsParams = Utils.getLayoutParams()
        instrumentsParams.gravity = Gravity.RIGHT or Gravity.TOP
        instrumentsParams.x = 100
        instrumentsParams.y = 40
    }

    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(instrumentsPanel)
        windowManager.removeView(drawer)
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        view!!.performClick()

        Log.i(TAG_NAME, "onTouch")



        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
//                initialX = instrumentsParams.x
//                initialY = instrumentsParams.y
//                initialTouchX = event.rawX
//                initialTouchY = event.rawY
//                moving = true
                if (instrumentsPanelVisible) {
                    windowManager.removeView(instrumentsPanel)
                } else {
                    windowManager.addView(instrumentsPanel, instrumentsParams)
                }
                instrumentsPanelVisible = !instrumentsPanelVisible
            }

            MotionEvent.ACTION_UP -> {
                Log.i(TAG_NAME, "ACTION_UP")

//                moving = false
            }

            MotionEvent.ACTION_MOVE -> {
//                instrumentsParams.x = initialX + (event.rawX - initialTouchX).toInt()
//                instrumentsParams.y = initialY + (event.rawY - initialTouchY).toInt()
//                windowManager.updateViewLayout(instrumentsPanel, instrumentsParams)
            }
        }
        return true
    }

    override fun onClick(v: View?) {
        Log.i(TAG_NAME, "onClick ${moving}")
    }

}