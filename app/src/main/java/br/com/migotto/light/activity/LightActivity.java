package br.com.migotto.light.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.migotto.light.R;
import br.com.migotto.light.adapter.RVLightAdapter;
import br.com.migotto.light.dao.DaoLight;
import br.com.migotto.light.model.Light;
import br.com.migotto.light.util.Message;

public class LightActivity extends AppCompatActivity {

    private EditText description;
    private DaoLight bdLight;
    private RecyclerView recyclerLight;
    private Context context;
    private View view;
    private List<Light> list=new ArrayList<>();
    private Light l=new Light();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        initComponets();
        context=getBaseContext();
        setUpList();
    }

    public void createList(){
        recyclerLight=(RecyclerView) findViewById(R.id.recycle_lights);
        recyclerLight.setLayoutManager(new LinearLayoutManager(context));
        recyclerLight.setHasFixedSize(true);
        RVLightAdapter adapter=new RVLightAdapter(list, context, onClickLight());
        recyclerLight.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=this.getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderIcon(android.R.drawable.ic_menu_sort_by_size);
        menu.setHeaderTitle(getString(R.string.context_menu_options));
    }

    public RVLightAdapter.LightOnClickListener onClickLight(){
        return new RVLightAdapter.LightOnClickListener(){
            @Override
            public void onClickListener(View view, int position) {
                l=list.get(position);
                registerForContextMenu(view);
                openContextMenu(view);
            }
        };
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(true){
            switch (item.getItemId()){
                case R.id.editar_menu:
                    description.setText(l.getDescription());
                    return true;
                case R.id.excluir_menu:
                    deleteDialog().show();
                    return true;
            }
        }
        return super.onContextItemSelected(item);
    }

    private AlertDialog deleteDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_delete_light))
                .setMessage(getString(R.string.message_delete_light))
                .setPositiveButton(getString(R.string.sim), onDelete())
                .setNegativeButton(getString(R.string.nao), onDelete())
                .setIcon(android.R.drawable.ic_menu_delete);
        return builder.create();
    }

    private DialogInterface.OnClickListener onDelete(){
        bdLight=new DaoLight(this);
        return new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        if(bdLight.delete(l)!=-1){
                            Message.toast(getBaseContext(), getString(R.string.sucesso_delete));
                            setUpList();
                            l=new Light();
                        }else{
                            Message.toast(getBaseContext(), getString(R.string.error_delete));
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialogInterface.dismiss();
                        break;
                }
            }
        };
    }

    private void initComponets(){
        description=(EditText)findViewById(R.id.description);
    }

    private void loadList() {
        bdLight = new DaoLight(context);
        list = bdLight.listAll();
    }

    private void setUpList(){
        loadList();
        createList();
    }

    private void resetForm(){
        description.setText("");
    }

    public void salvarNota(View view) {
        if(!description.getEditableText().toString().trim().isEmpty()){
            l.setDescription(description.getEditableText().toString().trim());
        }else{
            Toast.makeText(this, getString(R.string.validate), Toast.LENGTH_SHORT).show();
            description.requestFocus();
        }
        bdLight=new DaoLight(this);
        if(bdLight.save(l)!=-1){
            Toast.makeText(this, getString(R.string.sucesso_salvar), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, getString(R.string.erro_salvar), Toast.LENGTH_SHORT).show();
        }
        resetForm();
        setUpList();
        l=new Light();
    }
}
