package phamthanhhuong.com.baitapmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;

import java.util.ArrayList;

import phamthanhhuong.com.adapter.NhanVienAdapter;
import phamthanhhuong.com.model.NhanVien;

import static phamthanhhuong.com.baitapmenu.R.layout.detail_layout;

public class MainActivity extends AppCompatActivity {

    ListView lvNhanVien;
    NhanVienAdapter nhanVienAdapter;
    NhanVien selectedNV=null;
    ArrayList<NhanVien>dsNguon=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addEvents() {
        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedNV=nhanVienAdapter.getItem(i);
            }
        });
        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               selectedNV=nhanVienAdapter.getItem(i);
                return false;
            }
        });
    }

    private void addControls() {
        lvNhanVien=findViewById(R.id.lvNhanVien);
        nhanVienAdapter =new NhanVienAdapter(MainActivity.this,R.layout.item);
        lvNhanVien.setAdapter(nhanVienAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnuEdit)
        {
            hienThiManHinhEdit();
        }
        if(item.getItemId()==R.id.mnuRemove)
        {
            xuLyXoa();
        }
        return super.onContextItemSelected(item);
    }

    private void xuLyXoa() {
        nhanVienAdapter.remove(selectedNV);
    }


    private void hienThiManHinhEdit() {
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(detail_layout);

        final EditText edtMa=dialog.findViewById(R.id.edtMa);
        final EditText edtTen=dialog.findViewById(R.id.txtTen);
        RadioButton radMan=dialog.findViewById(R.id.radNam);
        final RadioButton radWoman=dialog.findViewById(R.id.radNu);
        Button btnLuu=dialog.findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nv=new NhanVien();
                selectedNV.setMa(edtMa.getText().toString());
                selectedNV.setTen(edtTen.getText().toString());
                selectedNV.setLaNu(radWoman.isChecked());
                nhanVienAdapter.notifyDataSetChanged();
                dialog.dismiss();
                dsNguon.clear();
                for(int i=0;i<nhanVienAdapter.getCount();i++)
                {
                    dsNguon.add(nhanVienAdapter.getItem(i));
                }
            }
        });
        edtMa.setText(selectedNV.getMa());
        edtTen.setText(selectedNV.getTen());
        if(selectedNV.isLaNu())
            radWoman.setChecked(true);
        else
            radMan.setChecked(true);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.option_menu,menu);
       MenuItem mnuSearch=menu.findItem(R.id.mnuSearch);
        SearchView searchView= (SearchView) mnuSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty())
                {
                    nhanVienAdapter.clear();
                    nhanVienAdapter.addAll(dsNguon);
                }
                ArrayList<NhanVien>dsTim=new ArrayList<>();
                for(NhanVien nv:dsNguon)
                {
                    if(nv.getMa().contains(s)||nv.getTen().contains(s))
                        dsTim.add(nv);
                }
                nhanVienAdapter.clear();
                nhanVienAdapter.addAll(dsTim);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnuNew:
                hienThiManHinhNhapNhanVien();
                break;
            case R.id.mnuHelp:
                break;
            case R.id.mnuAbout:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void hienThiManHinhNhapNhanVien() {
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(detail_layout);
        final EditText edtMa=dialog.findViewById(R.id.edtMa);
        final EditText edtTen=dialog.findViewById(R.id.txtTen);
        RadioButton radMan=dialog.findViewById(R.id.radNam);
        final RadioButton radWoman=dialog.findViewById(R.id.radNu);
        Button btnLuu=dialog.findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nv=new NhanVien();
                nv.setMa(edtMa.getText().toString());
                nv.setTen(edtTen.getText().toString());
                nv.setLaNu(radWoman.isChecked());
                nhanVienAdapter.add(nv);
                dialog.dismiss();
                dsNguon.clear();
                for(int i=0;i<nhanVienAdapter.getCount();i++)
                {
                    dsNguon.add(nhanVienAdapter.getItem(i));
                }

            }
        });
        dialog.show();
    }
}
