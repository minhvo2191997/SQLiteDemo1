package com.example.sqlitedemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlitedemo1.data.DBManager;
import com.example.sqlitedemo1.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtAddress;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private Button btnSave;
    private ListView lvStudent;
    private  DBManager dbManager;
    private ArrayAdapter adapter;
    private  List<Student> studentList;
    private  List<String> mList;
    private Button btnUpdate,btnRemove,btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager=new DBManager(this);
        initWidget();
        setAdapter();
        action();


    }

    private void initWidget() {
        edtName=findViewById(R.id.edtName);
        edtAddress=findViewById(R.id.edtAddress);
        edtPhoneNumber=findViewById(R.id.edtName);
        edtEmail=findViewById(R.id.edtEmail);
        btnSave=findViewById(R.id.btnSave);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnRemove=findViewById(R.id.btnRemove);
        btnClear=findViewById(R.id.btnClear);
        lvStudent=findViewById(R.id.lv);
    }

    private void setAdapter() {
        studentList=dbManager.getAllStudent();
        mList=getNameList();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,mList);
        lvStudent.setAdapter(adapter);
    }


    private  List<String> getNameList() {

        mList=new ArrayList<>();
        for (Student s:studentList) {
            mList.add(s.getmName());

        }
    return mList;
    }


    private void action() {
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Student student=createStudent();
                if(student!=null){
                    dbManager.addStudent(student);
                  adapter.notifyDataSetChanged();
                  setAdapter();
                }


            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();

            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student=studentList.get(position);
                edtName.setText(student.getmName().toString());
                edtAddress.setText(student.getmAddress().toString());
                edtEmail.setText(student.getnEmail().toString());
                edtPhoneNumber.setText(student.getmPhoneNumber().toString());
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s=new Student(edtName.getText().toString().trim(),
                        edtAddress.getText().toString(),edtPhoneNumber.getText().toString().trim(),
                        edtEmail.getText().toString().trim());


                Student student=studentList.get((int) lvStudent.getSelectedItemId());
                String name=student.getmName();
                if(dbManager.removeStudent(name.toString())){
                    Toast.makeText(MainActivity.this,"Xóa thành công",Toast.LENGTH_LONG).show();
                }

                setAdapter();
                clear();

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s=new Student(edtName.getText().toString().trim(),
                        edtAddress.getText().toString(),edtPhoneNumber.getText().toString().trim(),
                        edtEmail.getText().toString().trim());


                Student student=studentList.get((int) lvStudent.getSelectedItemId());
                String name=student.getmName();

                if(dbManager.updateStudent(s)){
                    Toast.makeText(MainActivity.this,"Cập nhật thành công",Toast.LENGTH_LONG).show();
                }

                setAdapter();
                clear();

            }
        });
    }

    private void clear() {
        edtName.setText("");
        edtPhoneNumber.setText("");
        edtEmail.setText("");
        edtAddress.setText("");
        lvStudent.setItemsCanFocus(false);

    }


    private Student createStudent(){
        String name=edtName.getText().toString();
        String address=edtAddress.getText().toString();
        String phoneNumber=edtPhoneNumber.getText().toString();
        String email=edtEmail.getText().toString();
        Student student=new Student(name,address,phoneNumber,email);
        return student;
    }
}