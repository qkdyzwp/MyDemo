package com.fir.wp.mydemo.ui;

import android.view.View;
import android.widget.Toast;
import com.fir.wp.mybase.dialog.ColorDialog;
import com.fir.wp.mybase.dialog.PromptDialog;
import com.fir.wp.mydemo.R;

/**
 * 作者：fir on 16/2/17 16:11
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public class DialogActivity extends ButtonActivity {
    private String[] buttons = {
            "Prompt Dialog",
            "Pic",
            "Text",
            "Text And Pic"
    };
    @Override
    public String[] getButtons() {
        return buttons;
    }
    @Override
    public void initView() {
        super.initView();
        this.title.setText("Dialog");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                showPromptDlg();
                break;
            case 1:
                showPicDialog();
                break;
            case 2:
                showTextDialog();
                break;
            case 3:
                showAllModeDialog();
                break;
            default:
                break;
        }

    }
    private void showPromptDlg() {
        new PromptDialog(this)
                .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                .setAnimationEnable(true)
                .setTitleText("Success").setContentText("Your info text goes here. Loremipsum dolor sit amet, consecteturn adipisicing elit, sed do eiusmod.")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }
    public void showTextDialog() {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setColor("#8ECB54");
        dialog.setAnimationEnable(true);
        dialog.setTitle(getString(R.string.operation));
        dialog.setContentText(getString(R.string.content_text));
        dialog.setPositiveListener(getString(R.string.text_iknow), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                Toast.makeText(DialogActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
    public void showPicDialog() {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setTitle(getString(R.string.operation));
        dialog.setAnimationEnable(true);
        dialog.setContentImage(getResources().getDrawable(R.mipmap.sample_img));
        dialog.setPositiveListener(getString(R.string.delete), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                Toast.makeText(DialogActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
            }
        })
                .setNegativeListener(getString(R.string.cancel), new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        Toast.makeText(DialogActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();
    }
    public void showAllModeDialog() {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setTitle(getString(R.string.operation));
        dialog.setAnimationEnable(true);
        dialog.setContentText(getString(R.string.content_text));
        dialog.setContentImage(getResources().getDrawable(R.mipmap.sample_img));
        dialog.setPositiveListener(getString(R.string.delete), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                Toast.makeText(DialogActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeListener(getString(R.string.cancel), new ColorDialog.OnNegativeListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                Toast.makeText(DialogActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
