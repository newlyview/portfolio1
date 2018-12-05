package hkit.kr.portfolio;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class OnlineActivity extends AppCompatActivity {
    EditText name0_btn, memo0_btn, phone0_btn;
    Button reset0_btn, email0_btn, go_home;
    ArrayAdapter sAdapter;
    Spinner spinner_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        name0_btn = findViewById(R.id.name0_btn);
        memo0_btn = findViewById(R.id.memo0_btn);
        phone0_btn = findViewById(R.id.phone0_btn);
        reset0_btn = findViewById(R.id.reset0_btn);
        reset0_btn.setOnClickListener(reset0_btn_click);
        email0_btn = findViewById(R.id.email0_btn);
        email0_btn.setOnClickListener(email0_btn_click);
        spinner_select = findViewById(R.id.spinner_select);
        //스피너 연결(어댑터이용)
        sAdapter = ArrayAdapter.createFromResource(this,R.array.question, android.R.layout.simple_spinner_dropdown_item);
        spinner_select.setAdapter(sAdapter);

        Intent intent = getIntent();
        intent.getStringExtra("subject");
        go_home=findViewById(R.id.go_home);
        go_home.setOnClickListener(go_home_click);
    }
    View.OnClickListener reset0_btn_click =  new View.OnClickListener() {
        @Override //오류 왜??
        public void onClick(View view) {
           name0_btn.setText("");
           memo0_btn.setText("");
           phone0_btn.setText("");
        }
    };
    View.OnClickListener email0_btn_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String bodyText = "<상담정보>";
            bodyText = "이름 : " + name0_btn.getText().toString();
            bodyText += "상담내역 :" + memo0_btn.getText().toString();
            bodyText += "전화번호 :" + phone0_btn.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            String address = "dollbeeeeee@naver.com"; //받을이메일주소값
            email.putExtra(Intent.EXTRA_EMAIL, address);
            email.putExtra(Intent.EXTRA_SUBJECT, spinner_select + "의 가입정보");
            email.putExtra(Intent.EXTRA_TEXT, bodyText);

            startActivity(Intent.createChooser(email, "SendEmail"));
        }
    };
    View.OnClickListener go_home_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };
}
