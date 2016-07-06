package fylder.permissions.demo.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import fylder.permissions.demo.R;

/**
 * 获取权限工具
 * Created by 剑指锁妖塔 on 2016/7/6.
 */
public class PermissionTools {

    /**
     * 获取文件读写的权限
     *
     * @param REQUEST 回调码
     */
    public static boolean requestStorage(Activity context, int REQUEST) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            PermissionTools.requestPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE, context.getString(R.string.permission_read_storage_rationale), REQUEST);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取指定权限
     *
     * @param permission 权限
     *                   Manifest.permission.READ_EXTERNAL_STORAGE
     * @param REQUEST    回调码
     */
    public static boolean request(String permission, Activity context, int REQUEST) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            PermissionTools.requestPermission(context, permission, context.getString(R.string.permission_read_storage_rationale), REQUEST);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Requests given permission.
     * If the permission has been denied previously, a Dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     * <p>
     * 还没赋予权限给予弹窗提醒
     */
    private static void requestPermission(final Activity activity, final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            showAlertDialog(activity, activity.getString(R.string.permission_title_rationale), rationale,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                        }
                    }, activity.getString(R.string.label_ok), null, activity.getString(R.string.label_cancel));
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
    }

    /**
     * This method shows dialog with given title & message.
     * Also there is an option to pass onClickListener for positive & negative button.
     *
     * @param title                         - dialog title
     * @param message                       - dialog message
     * @param onPositiveButtonClickListener - listener for positive button
     * @param positiveText                  - positive button text
     * @param onNegativeButtonClickListener - listener for negative button
     * @param negativeText                  - negative button text
     */
    private static void showAlertDialog(Context context, @Nullable String title, @Nullable String message,
                                        @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                        @NonNull String positiveText,
                                        @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                        @NonNull String negativeText) {
        AlertDialog mAlertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
        builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
        mAlertDialog = builder.show();
    }
}
