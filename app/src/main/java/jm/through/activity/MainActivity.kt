package jm.through.activity

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import jm.through.R
import jm.through.read.MessageFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sharedpreference 기능
        val sp: SharedPreferences? = getSharedPreferences("sp", Context.MODE_PRIVATE)
        var sp_id: String = ""
        var sp_pwd: String = ""


        //이미 회원가입이 되어있을 시
        if (sp != null) {
            sp_id = sp?.getString("id", "")
            sp_pwd = sp?.getString("pwd", "")
            edit_id.setText(sp_id)
            edit_pwd.setText(sp_pwd)
        }


        //sign버튼 눌렀을 때
        btn_sign.setOnClickListener {
            var intent = Intent(applicationContext, SignActivity::class.java)
            startActivity(intent)
            finish()
        }


        //login버튼 눌렀을 때
        btn_login.setOnClickListener {
            //id값을 edit에서 가져옴
            var id = edit_id.text.toString()
            var pwd = edit_pwd.text.toString()

            if (id != "" && pwd != "") {
                if (id == sp_id && pwd == sp_pwd) {
                    var intent = Intent(applicationContext, MailActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "아이디나 패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "아이디와 패스워드를 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }


    /**뒤로가기키 메인에서 누를 시 다이얼로그 출력**/
    override fun onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() > 0){
//            getFragmentManager().popBackStack();
//        }else {
//            super.onBackPressed()
//        }
        Log.v("MainActivity : ","onBackPressed Called")
        super.onBackPressed();
//
//
//        @Override
//        public void onBackPressed() {
//            if ( pressedTime == 0 ) {
//                Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
//                pressedTime = System.currentTimeMillis();
//            }
//            else {
//                int seconds = (int) (System.currentTimeMillis() - pressedTime);
//
//                if ( seconds > 2000 ) {
//                    Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
//                    pressedTime = 0 ;
//                }
//                else {
//                    super.onBackPressed();
////                finish(); // app 종료 시키기
//                }
//            }
//        }


       
    }


}