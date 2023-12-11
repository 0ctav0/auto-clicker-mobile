package phoenix.autoclickermobile

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.Button


class Overlay : Service(), OnTouchListener, OnClickListener {

    private var topLeftView: View? = null
    private var overlayedButton: Button? = null
    private var wm: WindowManager? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        val a = createWindowContext(display, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager?;
        overlayedButton = Button(this);
        overlayedButton!!.setOnTouchListener(this);
        overlayedButton!!.setAlpha(0.0f);
        overlayedButton!!.setBackgroundColor(0x55fe4444);
        overlayedButton!!.setOnClickListener(this);

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        )
        params.gravity = Gravity.LEFT or Gravity.TOP
        params.x = 0
        params.y = 0
        wm!!.addView(overlayedButton, params)

        topLeftView = View(this)
        val topLeftParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        )
        topLeftParams.gravity = Gravity.LEFT or Gravity.TOP
        topLeftParams.x = 0
        topLeftParams.y = 0
        topLeftParams.width = 0
        topLeftParams.height = 0
        wm!!.addView(topLeftView, topLeftParams)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (overlayedButton != null) {
            wm?.removeView(overlayedButton);
            wm?.removeView(topLeftView);
            overlayedButton = null;
            topLeftView = null;
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}