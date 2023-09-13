package com.example.semana04project2

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private val CAMERA_REQUEST_CODE = 0
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCamera = findViewById<Button>(R.id.btCamera)

        btCamera.setOnClickListener{
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
            requestCameraPermission()
        }else{
            Toast.makeText(this, "Ya tiene permiso!!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            //ya rechazo el permiso antes. Debe habilitarlo MANUALMENTE
            Toast.makeText(this, "ya rechazo el permiso antes. Debe habilitarlo MANUALMENTE", Toast.LENGTH_LONG).show()
        }else {
            // nunca pidio permiso. Lo solicito
            Toast.makeText(this, "nunca pidio permiso. Acepte o rechace el permiso", Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            CAMERA_REQUEST_CODE ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //PERMISO OTORGADO
                    Toast.makeText(this, "Se otorgo el permiso", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Permiso NEGADO!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}