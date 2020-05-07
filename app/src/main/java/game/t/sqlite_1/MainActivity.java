package game.t.sqlite_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        DB db=new DB(MainActivity.this);
        final TextView show=findViewById(R.id.tff);
        final EditText id=findViewById(R.id.idd);
        final EditText name=findViewById(R.id.editText);
        final EditText content=findViewById(R.id.editText2);
        //final TextView show=findViewById(R.id.tff);
        Button push=findViewById(R.id.button);
        Button update=findViewById(R.id.button2);
        Button pop=findViewById(R.id.button3);

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getter_setter gs=new getter_setter();
                DB db=new DB(MainActivity.this);
                gs.setName(""+name.getText());
                gs.setContent(""+content.getText());
                if(name.getText().toString().isEmpty() || content.getText().toString().isEmpty()){
                    name.setError("Required");
                    content.setError("Required");
                    return;
                }
                db.addcontact(gs);
                Toast.makeText(MainActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();

                show.setText("");

                List<getter_setter> get=db.getAllContacts();
                for(getter_setter g:get){
                    show.append(g.getId()+" "+g.getName()+" "+g.getContent()+"\n");
                    Log.d("Abhi",g.getId()+" "+g.getName()+" "+g.getContent());
                }
                Toast.makeText(MainActivity.this, "kilop "+db.count(), Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().toString().isEmpty()){
                    id.setError("Cant be zero");
                    return;
                }
                if(name.getText().toString().isEmpty() || content.getText().toString().isEmpty()){
                    name.setError("Required");
                    content.setError("Required");
                    return;
                }

                getter_setter gs=new getter_setter();
                DB db=new DB(MainActivity.this);
                gs.setName(""+name.getText());
                gs.setContent(""+content.getText());
                gs.setId(Integer.parseInt(id.getText().toString()));
                db.updateContact(gs);

                int affected=db.updateContact(gs);
                show.setText("");

                List<getter_setter> get=db.getAllContacts();
                for(getter_setter g:get){
                    show.append(g.getId()+" "+g.getName()+" "+g.getContent()+"\n");
                    Log.d("Abhi",g.getId()+" "+g.getName()+" "+g.getContent());
                }
                Toast.makeText(MainActivity.this, " "+affected, Toast.LENGTH_SHORT).show();
            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().toString().isEmpty()){
                    id.setError("Cant be zero");
                    return;
                }
                getter_setter gs=new getter_setter();
                DB db=new DB(MainActivity.this);
                gs.setId(Integer.parseInt(id.getText().toString()));
                db.deleteContact(gs);

                int affected=db.updateContact(gs);
                show.setText("");

                List<getter_setter> get=db.getAllContacts();
                for(getter_setter g:get){
                    show.append(g.getId()+" "+g.getName()+" "+g.getContent()+"\n");
                    Log.d("Abhi",g.getId()+" "+g.getName()+" "+g.getContent());
                }
                Toast.makeText(MainActivity.this, "uccesfully delted", Toast.LENGTH_SHORT).show();

            }
        });

        List<getter_setter> get=db.getAllContacts();
        for(getter_setter g:get){
            show.append(g.getId()+" "+g.getName()+" "+g.getContent()+"\n");
            Log.d("Abhi",g.getId()+" "+g.getName()+" "+g.getContent());
        }
        Toast.makeText(MainActivity.this, "Msg succesfully done!", Toast.LENGTH_SHORT).show();

    }
}
