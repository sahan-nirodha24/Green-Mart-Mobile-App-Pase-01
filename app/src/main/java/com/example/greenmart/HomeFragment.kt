package com.example.greenmart

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var scrollView: HorizontalScrollView
    private val handler = Handler(Looper.getMainLooper())
    private var scrollPosition = 0
    private val scrollSpeed = 15 // Speed of scrolling
    private val delayMillis: Long = 30 // Delay for each scroll step

    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            scrollPosition += scrollSpeed
            scrollView.smoothScrollTo(scrollPosition, 0)

            // If the scroll reaches the end, reset to start
            if (scrollPosition >= scrollView.getChildAt(0).width - scrollView.width) {
                scrollPosition = 0
            }

            handler.postDelayed(this, delayMillis)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the scrollView after view creation
        scrollView = view.findViewById(R.id.horizontalScrollView)

        // Start auto-scrolling
        handler.postDelayed(autoScrollRunnable, delayMillis)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Stop handler to prevent memory leaks
        handler.removeCallbacks(autoScrollRunnable)
    }
}
