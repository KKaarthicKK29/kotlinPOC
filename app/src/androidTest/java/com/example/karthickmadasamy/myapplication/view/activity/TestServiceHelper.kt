package com.example.karthickmadasamy.myapplication.view.activity

import android.content.Context
import java.io.BufferedReader
import java.io.InputStream


object TestServiceHelper {

    @Throws(Exception::class)
    fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(inputStream.reader())
        val content = StringBuilder()
        try {
            var line = reader.readLine()
            while (line != null) {
                content.append(line)
                line = reader.readLine()
            }
        } finally {
            reader.close()
        }

        return content.toString()
    }

    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String): String {
        val stream = context.getResources().getAssets().open(filePath)

        val ret = convertStreamToString(stream)
        //Make sure to close all streams.
        stream.close()
        return ret
    }
}