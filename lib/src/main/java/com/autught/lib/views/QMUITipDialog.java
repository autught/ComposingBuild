package com.autught.lib.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import com.autught.lib.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 提供一个浮层展示在屏幕中间, 一般使用 {@link Builder} 或 {@link CustomBuilder} 生成。
 * <ul>
 * <li>{@link Builder} 提供了一个图标和一行文字的样式, 其中图标有几种类型可选, 见 {@link Builder.IconType}</li>
 * <li>{@link CustomBuilder} 支持传入自定义的 layoutResId, 达到自定义 TipDialog 的效果。</li>
 * </ul>
 *
 * @author cginechen
 * @date 2016-10-14
 */

public class QMUITipDialog extends AppCompatDialog {


    public QMUITipDialog(Context context) {
        this(context, R.style.QMUI_TipDialog);
    }

    public QMUITipDialog(Context context, int themeResId) {
        super(context, themeResId);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void dismiss() {
        Context context = getContext();
        if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            super.dismiss();
        } else {
            try {
                super.dismiss();
            } catch (Throwable ignore) {
            }
        }
    }

    /**
     * 生成默认的 {@link QMUITipDialog}
     * <p>
     * 提供了一个图标和一行文字的样式, 其中图标有几种类型可选。见 {@link IconType}
     * </p>
     *
     * @see CustomBuilder
     */
    public static class Builder {
        /**
         * 不显示任何icon
         */
        public static final int ICON_TYPE_NOTHING = 0;
        /**
         * 显示 Loading 图标
         */
        public static final int ICON_TYPE_LOADING = 1;
        /**
         * 显示成功图标
         */
        public static final int ICON_TYPE_SUCCESS = 2;
        /**
         * 显示失败图标
         */
        public static final int ICON_TYPE_FAIL = 3;
        /**
         * 显示信息图标
         */
        public static final int ICON_TYPE_INFO = 4;

        @IntDef({ICON_TYPE_NOTHING, ICON_TYPE_LOADING, ICON_TYPE_SUCCESS, ICON_TYPE_FAIL, ICON_TYPE_INFO})
        @Retention(RetentionPolicy.SOURCE)
        public @interface IconType {
        }

        private @IconType int mCurrentIconType = ICON_TYPE_NOTHING;

        private final Context mContext;

        private CharSequence mTipWord;


        public Builder(Context context) {
            mContext = context;
        }

        /**
         * 设置 icon 显示的内容
         *
         * @see IconType
         */
        public Builder setIconType(@IconType int iconType) {
            mCurrentIconType = iconType;
            return this;
        }

        /**
         * 设置显示的文案
         */
        public Builder setTipWord(CharSequence tipWord) {
            mTipWord = tipWord;
            return this;
        }

        public QMUITipDialog create() {
            return create(true);
        }

        public QMUITipDialog create(boolean cancelable) {
            return create(cancelable, R.style.QMUI_TipDialog);
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 {@link Dialog#show()} 方法
         *
         * @param cancelable 按系统返回键是否可以取消
         * @return 创建的 Dialog
         */
        public QMUITipDialog create(boolean cancelable, int style) {
            QMUITipDialog dialog = new QMUITipDialog(mContext, style);
            dialog.setCancelable(cancelable);
            Context dialogContext = dialog.getContext();
            LinearLayoutCompat dialogView = generateContainer(dialogContext);

            if (mCurrentIconType == ICON_TYPE_LOADING) {
                QMUILoadingView loadingView = new QMUILoadingView(dialogContext);
                loadingView.setColor(Color.WHITE);

                loadingView.setSize(32);

                dialogView.addView(loadingView, onCreateIconOrLoadingLayoutParams(dialogContext));

            } else if (mCurrentIconType == ICON_TYPE_SUCCESS ||
                    mCurrentIconType == ICON_TYPE_FAIL ||
                    mCurrentIconType == ICON_TYPE_INFO) {
                ImageView imageView = new AppCompatImageView(dialogContext);
                Drawable drawable;
                if (mCurrentIconType == ICON_TYPE_SUCCESS) {
                    drawable = ContextCompat.getDrawable(dialogContext, R.drawable.qmui_icon_notify_done);

                } else if (mCurrentIconType == ICON_TYPE_FAIL) {
                    drawable = ContextCompat.getDrawable(dialogContext, R.drawable.qmui_icon_notify_error);

                } else {
                    drawable = ContextCompat.getDrawable(dialogContext, R.drawable.qmui_icon_notify_info);
                }
                imageView.setImageDrawable(drawable);
                dialogView.addView(imageView, onCreateIconOrLoadingLayoutParams(dialogContext));
            }

            if (mTipWord != null && mTipWord.length() > 0) {
                AppCompatTextView tipView = new AppCompatTextView(dialogContext);
                tipView.setEllipsize(TextUtils.TruncateAt.END);
                tipView.setId(R.id.tip_content_id);
                tipView.setGravity(Gravity.CENTER);
                tipView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tipView.setTextColor(Color.WHITE);
                tipView.setText(mTipWord);

                dialogView.addView(tipView, onCreateTextLayoutParams(dialogContext, mCurrentIconType));
            }
            dialog.setContentView(dialogView);
            return dialog;
        }

        protected LinearLayoutCompat generateContainer(Context context) {
            LinearLayoutCompat container = new LinearLayoutCompat(context);
//            int radius = QMUIResHelper.getAttrDimen(context, R.attr.qmui_tip_dialog_radius);
//            Drawable background = QMUIResHelper.getAttrDrawable(context, R.attr.qmui_skin_support_tip_dialog_bg);
//            int paddingHor = QMUIResHelper.getAttrDimen(context, R.attr.qmui_tip_dialog_padding_horizontal);
//            int paddingVer = QMUIResHelper.getAttrDimen(context, R.attr.qmui_tip_dialog_padding_vertical);
//            container.setBackground(background);
//            container.setPadding(paddingHor, paddingVer, paddingHor, paddingVer);
//            container.setRadius(radius);
            return container;
        }

        protected LinearLayout.LayoutParams onCreateIconOrLoadingLayoutParams(Context context) {
            return new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        protected LinearLayout.LayoutParams onCreateTextLayoutParams(Context context, @IconType int iconType) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (iconType != ICON_TYPE_NOTHING) {
                lp.topMargin = 10;
            }
            return lp;
        }

    }

    /**
     * CustomBuilder with xml layout
     */
    public static class CustomBuilder {
        private final Context mContext;
        private int mContentLayoutId;

        public CustomBuilder(Context context) {
            mContext = context;
        }

        public CustomBuilder setContent(@LayoutRes int layoutId) {
            mContentLayoutId = layoutId;
            return this;
        }

        public QMUITipDialog create() {
            QMUITipDialog dialog = new QMUITipDialog(mContext);
            Context dialogContext = dialog.getContext();
            LinearLayoutCompat tipDialogView = generateContainer(dialogContext);
            LayoutInflater.from(dialogContext).inflate(mContentLayoutId, tipDialogView, true);
            dialog.setContentView(tipDialogView);
            return dialog;
        }

        protected LinearLayoutCompat generateContainer(Context context) {
            LinearLayoutCompat container = new LinearLayoutCompat(context);
//            int radius = QMUIResHelper.getAttrDimen(context, R.attr.qmui_tip_dialog_radius);
//            Drawable background = QMUIResHelper.getAttrDrawable(context, R.attr.qmui_skin_support_tip_dialog_bg);
//            int paddingHor = QMUIResHelper.getAttrDimen(context, R.attr.qmui_tip_dialog_padding_horizontal);
//            int paddingVer = QMUIResHelper.getAttrDimen(context, R.attr.qmui_tip_dialog_padding_vertical);
//            container.setBackground(background);
//            container.setPadding(paddingHor, paddingVer, paddingHor, paddingVer);
//            container.setRadius(radius);
            return container;
        }
    }
}