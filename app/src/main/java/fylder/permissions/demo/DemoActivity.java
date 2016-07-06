package fylder.permissions.demo;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fylder.permissions.demo.tools.PermissionTools;

public class DemoActivity extends AppCompatActivity {

    private static final int STORAGE_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.demo_storage)
    void test() {

        if (PermissionTools.requestStorage(this, STORAGE_CODE)) {
            Toast.makeText(this, getString(R.string.storage_tip), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.demo_camera)
    void test2() {

        if (PermissionTools.request(Manifest.permission.CAMERA, this, STORAGE_CODE)) {
            Toast.makeText(this, getString(R.string.camera_tip), Toast.LENGTH_SHORT).show();
        }
    }
}
