package cn.feng.skin.demo.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.bedrock.permissionrequestor.PermissionsRequestor;

import cn.feng.skin.demo.R;
import cn.feng.skin.demo.fragment.ArticleListFragment;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		PermissionsRequestor(this).request(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INSTALL_PACKAGES))

		new PermissionsRequestor(this).request(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});

		initFragment();
	}

	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		if(fragment == null){
			fragment = ArticleListFragment.newInstance();
			fm.beginTransaction()
				.add(R.id.fragment_container, fragment)
				.commit();
		}
	}
}
