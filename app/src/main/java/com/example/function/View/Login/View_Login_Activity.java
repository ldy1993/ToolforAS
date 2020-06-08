package com.example.function.View.Login;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ldy.utils.ReflactUtils;
import com.ldy.view.CustomWidget.Keyboard.CustomKeyboard;
import com.ldy.study.R;



public class View_Login_Activity extends Activity {
    private Button bt_login;
    private RelativeLayout rl_logo;
    private EditText et_operator_id;
    private EditText et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__login);
        bt_login=findViewById(R.id.bt_login);
        rl_logo=findViewById(R.id.rl_logo);
        et_operator_id=findViewById(R.id.et_operator_id);
        et_password=findViewById(R.id.et_password);
        bindAnimation();
    }
    /**
     * define the login fragment's animation when the keyboard is showing or
     * hiding
     **/
    @SuppressLint("NewApi")
    private void bindAnimation() {
        CustomKeyboard.getInstance(this, null).setKeyboardEvent(
                new CustomKeyboard.KeyboardEvent() {
                    @Override
                    public void onShow() {
                        ViewGroup.LayoutParams start = rl_logo.getLayoutParams();
                        LinearLayout.LayoutParams end = new LinearLayout.LayoutParams(
                                start);
                        end.height = start.height - 160;
                        rl_logo.setLayoutParams(end);
                        ObjectAnimator animator = new ObjectAnimator();
                        animator.setPropertyName("layoutParams");
                        animator.setObjectValues(start, end);
                        animator.setTarget(rl_logo);
                        animator.setInterpolator(new LinearInterpolator());
                        final int startPaddingTop = rl_logo.getPaddingTop();
                        final int startPaddingBottom = rl_logo
                                .getPaddingBottom();
                        animator.setEvaluator(new TypeEvaluator<ViewGroup.LayoutParams>() {
                            @Override
                            public ViewGroup.LayoutParams evaluate(float fraction,
                                                                   ViewGroup.LayoutParams startValue,
                                                                   ViewGroup.LayoutParams endValue) {
                                int height = (int) (startValue.height + fraction
                                        * (endValue.height - startValue.height));
                                int paddingTop = (int) (startPaddingTop + fraction
                                        * (endValue.height - startValue.height)
                                        * 0.478125);
                                int paddingBottom = (int) (startPaddingBottom + fraction
                                        * (endValue.height - startValue.height)
                                        * 0.478125);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        startValue);
                                params.height = height;
                                rl_logo.setPadding(0, paddingTop, 0,
                                        paddingBottom);
                                return params;
                            }
                        });
                        animator.setDuration(200);
                        // the LayoutParams type is not matched, so {@link
                        // ObjectAnimator.class} can not find it, we should
                        // handy define it.
                        if (Build.VERSION.SDK_INT < 21) {
                            ReflactUtils.setFieldValue(
                                    Class.class,
                                    ReflactUtils
                                            .<PropertyValuesHolder[]> getFieldValue(
                                                    ValueAnimator.class,
                                                    animator, "mValues")[0],
                                    "mValueType", ViewGroup.LayoutParams.class);
                        } else {
                            ReflactUtils
                                    .<PropertyValuesHolder[]> getFieldValue(
                                            ValueAnimator.class, animator,
                                            "mValues")[0]
                                    .setConverter(new TypeConverter<LinearLayout.LayoutParams, ViewGroup.LayoutParams>(
                                            LinearLayout.LayoutParams.class,
                                            ViewGroup.LayoutParams.class) {
                                        @Override
                                        public ViewGroup.LayoutParams convert(
                                                android.widget.LinearLayout.LayoutParams value) {
                                            return (ViewGroup.LayoutParams) value;
                                        }
                                    });
                        }
                        animator.start();
                    }

                    @Override
                    public void onHide() {
                        ViewGroup.LayoutParams start = rl_logo.getLayoutParams();
                        LinearLayout.LayoutParams end = new LinearLayout.LayoutParams(
                                start);
                        end.height = start.height + 160;
                        rl_logo.setLayoutParams(end);
                        ObjectAnimator animator = new ObjectAnimator();
                        animator.setPropertyName("layoutParams");
                        animator.setDuration(200);
                        animator.setObjectValues(start, end);
                        animator.setTarget(rl_logo);
                        animator.setInterpolator(new DecelerateInterpolator());
                        final int startPaddingTop = rl_logo.getPaddingTop();
                        final int startPaddingBottom = rl_logo
                                .getPaddingBottom();
                        animator.setEvaluator(new TypeEvaluator<ViewGroup.LayoutParams>() {
                            @Override
                            public ViewGroup.LayoutParams evaluate(float fraction,
                                                                   ViewGroup.LayoutParams startValue,
                                                                   ViewGroup.LayoutParams endValue) {
                                int height = (int) (startValue.height + fraction
                                        * (endValue.height - startValue.height));
                                int paddingTop = (int) (startPaddingTop + fraction
                                        * (endValue.height - startValue.height)
                                        * 0.478125);
                                int paddingBottom = (int) (startPaddingBottom + fraction
                                        * (endValue.height - startValue.height)
                                        * 0.478125);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        startValue);
                                params.height = height;
                                rl_logo.setPadding(0, paddingTop, 0,
                                        paddingBottom);
                                return params;
                            }
                        });
//                        if (Build.VERSION.SDK_INT < 21) {
//                            ReflactUtils.setFieldValue(
//                                    Class.class,
//                                    ReflactUtils
//                                            .<PropertyValuesHolder[]> getFieldValue(
//                                                    ValueAnimator.class,
//                                                    animator, "mValues")[0],
//                                    "mValueType", ViewGroup.LayoutParams.class);
//                        } else {
//                            ReflactUtils
//                                    .<PropertyValuesHolder[]> getFieldValue(
//                                            ValueAnimator.class, animator,
//                                            "mValues")[0]
//                                    .setConverter(new TypeConverter<LinearLayout.LayoutParams, ViewGroup.LayoutParams>(
//                                            LinearLayout.LayoutParams.class,
//                                            ViewGroup.LayoutParams.class) {
//                                        @Override
//                                        public ViewGroup.LayoutParams convert(
//                                                android.widget.LinearLayout.LayoutParams value) {
//                                            return (ViewGroup.LayoutParams) value;
//                                        }
//                                    });
//                        }
                        animator.start();
                    }
                });
    }

}
