package com.ldy.View.CustomWidget.FrameLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import SRnO.Tool.aar.R;


public class InputAmt extends FrameLayout implements OnClickListener {
    public enum Keyboard_Type {
        /**
         * 支付密码键盘
         */
        PAY,
        /**
         * 退货密码键盘
         */
        REFUND,
        /**
         * 扫码密码键盘
         */
        SCAN_VOID,
        /**
         * 快速预授权密码键盘
         */
        FAST_AUTH
    }
    private final int bankcard=1;
    private final int scan=2;
    public Context parentContext;
    public String text = "";
    public String preText = "";
    public TextView textView;
    public TextView amountSign;
    public TextView amount_consume;
    public TextView text_scanpay, text_cardpay;
    public ClickListener listener;
    int lenth = 10;
    boolean isPrice = false;
    Keyboard_Type keyboardType = Keyboard_Type.REFUND;

    public String getDigitAmount() {
        if (text.length() > 0) {
            return String.valueOf(Double.valueOf(text) / 100);
        } else {
            return "0";
        }
    }

    public String getAmount() {
        if (text.length() > 0) {
            return textView.getText().toString();
        } else {
            return "0";
        }
    }

    public void  setListener(ClickListener listener)
    {
        this.listener=listener;
    }
    public interface ClickListener
    {
        void onClick(int id);
    }
    public InputAmt(Context context) {
        super(context);
        parentContext = context;
    }

    public InputAmt(Context context, AttributeSet attrs) {
        super(context, attrs);
        parentContext = context;
    }

    public InputAmt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        parentContext = context;
    }

    public void init(EditText text) {
        LayoutInflater.from(parentContext).inflate(R.layout.soft_num_board,
                this);
        this.findViewById(R.id.btn0).setOnClickListener(this);
        this.findViewById(R.id.btn1).setOnClickListener(this);
        this.findViewById(R.id.btn2).setOnClickListener(this);
        this.findViewById(R.id.btn3).setOnClickListener(this);
        this.findViewById(R.id.btn4).setOnClickListener(this);
        this.findViewById(R.id.btn5).setOnClickListener(this);
        this.findViewById(R.id.btn6).setOnClickListener(this);
        this.findViewById(R.id.btn7).setOnClickListener(this);
        this.findViewById(R.id.btn8).setOnClickListener(this);
        this.findViewById(R.id.btn9).setOnClickListener(this);
        this.findViewById(R.id.btnDel).setOnClickListener(this);
        this.findViewById(R.id.btn_eliminate).setOnClickListener(this);
        this.findViewById(R.id.btn00).setOnClickListener(this);
        this.findViewById(R.id.btn_clear).setOnClickListener(this);
        this.findViewById(R.id.btn_point).setOnClickListener(this);
        this.findViewById(R.id.button_ok).setOnClickListener(this);
        amountSign = (TextView) this.findViewById(R.id.textview_amount_sign);
        if (text == null) {
            textView = (TextView) this.findViewById(R.id.textview_amount);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView = text;
        }
        amount_consume = (TextView) this.findViewById(R.id.textview_amount_consume);
        if (keyboardType.equals(Keyboard_Type.PAY)) {
            this.findViewById(R.id.btn_scan).setVisibility(View.VISIBLE);
            this.findViewById(R.id.btn_clear).setVisibility(View.GONE);
            text_scanpay = (TextView) this.findViewById(R.id.text_scanpay);
            if (text_scanpay != null) {
                if (getDisplayMetrics(parentContext) == 720) {
                    text_scanpay.setTextSize(16);
                } else if (getDisplayMetrics(parentContext) == 480) {
                    text_scanpay.setTextSize(10);
                }
                text_scanpay.setText("扫码支付");
            }
            if (amount_consume != null) {
                amount_consume.setText("缴费金额");
            }
        } else if (keyboardType.equals(Keyboard_Type.REFUND)) {
            this.findViewById(R.id.btn_scan).setVisibility(View.GONE);
            this.findViewById(R.id.btn_clear).setVisibility(View.VISIBLE);
            text_cardpay = (TextView) this.findViewById(R.id.text_cardpay);
            if (text_cardpay != null) {
                if (getDisplayMetrics(parentContext) == 720) {
                    text_cardpay.setTextSize(20);
                } else if (getDisplayMetrics(parentContext) == 480) {
                    text_cardpay.setTextSize(16);
                }
                text_cardpay.setText("确认");
            }
            if (amount_consume != null) {
                amount_consume.setText("缴费金额");
            }
        } else if (keyboardType.equals(Keyboard_Type.SCAN_VOID)) {
            this.findViewById(R.id.btn_scan).setVisibility(View.VISIBLE);
            this.findViewById(R.id.btn_clear).setVisibility(View.VISIBLE);
            this.findViewById(R.id.button_ok).setVisibility(View.GONE);
            text_scanpay = (TextView) this.findViewById(R.id.text_scanpay);
            if (text_scanpay != null) {
                if (getDisplayMetrics(parentContext) == 720) {
                    text_scanpay.setTextSize(20);
                } else if (getDisplayMetrics(parentContext) == 480) {
                    text_scanpay.setTextSize(16);
                }
                text_scanpay.setText("确认");
            }
            if (amount_consume != null) {
                amount_consume.setText("退款金额");
            }
            text_scanpay = (TextView) this.findViewById(R.id.text_scanpay);
            if (text_scanpay != null) {
                if (getDisplayMetrics(parentContext) == 720) {
                    text_scanpay.setTextSize(20);
                } else if (getDisplayMetrics(parentContext) == 480) {
                    text_scanpay.setTextSize(16);
                }
                text_scanpay.setText("扫码支付");
            }
        } else if (keyboardType.equals(Keyboard_Type.FAST_AUTH)) {
            this.findViewById(R.id.btn_scan).setVisibility(View.VISIBLE);
            this.findViewById(R.id.btn_clear).setVisibility(View.GONE);
            text_cardpay = (TextView) this.findViewById(R.id.text_cardpay);
            if (text_cardpay != null) {
                if (getDisplayMetrics(parentContext) == 720) {
                    text_cardpay.setTextSize(20);
                } else if (getDisplayMetrics(parentContext) == 480) {
                    text_cardpay.setTextSize(16);
                }
                text_cardpay.setText("确认");
            }
            text_scanpay = (TextView) this.findViewById(R.id.text_scanpay);
            if (text_scanpay != null) {
                if (getDisplayMetrics(parentContext) == 720) {
                    text_scanpay.setTextSize(16);
                } else if (getDisplayMetrics(parentContext) == 480) {
                    text_scanpay.setTextSize(10);
                }
                text_scanpay.setText("快速预授权");
            }
            if (amount_consume != null) {
                amount_consume.setText("金额");
            }
        }
    }

    public void setAttri(boolean price, int len) {
        isPrice = price;
        lenth = len;
        if (isPrice) {
            // textView.setText(preText+"0.00");
            textView.setText(preText);
        }
    }

//	public void setAmountSign (String sign) {
//		if(!StringUtils.isEmpty(sign))
//			amountSign.setText(sign);
//	}

    public void setKeyboarType(Keyboard_Type type) {
        this.keyboardType = type;
    }

    /**
     * 显示尚可圈存金额
     *
     * @param amount
     */
    public void setEcLoadAmount(String amount) {
        TextView balanceTextView;

        balanceTextView = (TextView) this.findViewById(R.id.textview_ecload_amount);
        balanceTextView.setVisibility(View.VISIBLE);
        balanceTextView.setText(amount);
    }

    public void changetext(char w) {
        // 退格
        if (w == 0x0f) {
            if (text.length() >= 1) {
                text = text.substring(0, text.length() - 1);
            }
        }
        // 清空
        if (w == 0x18) {
            if (text.length() >= 1) {
                text = "";
            }
        }

        // 最大长度
        if (text.length() >= lenth) {
            return;
        }
        if (!text.isEmpty()) {
            if (!text.contains(".")) {

                //新增小数点，控制只有一个小数点
                if (!isPrice) {
                    if (w == '.') {

                        text += w;

                    }
                }
            } else {
                if (text.substring(text.indexOf(".")).length() > 2) {
                    return;
                }
            }
        }
        if ((w >= '0' && w <= '9')) {

            if (isPrice) {
                // 金额不为0
                if (w != '0' || text.length() != 0) {
                    text += w;
                }
            } else {
                text += w;
            }
        }
        //如果第一位为0，
        if(!text.isEmpty()&& "0".equals(text.substring(0, 1)))
        {
            //第二位不为.的话，把第一位的0去掉
            if(!text.contains(".")&&text.length()>1)
            {
                text=text.substring(1);
            }

        }
        if (isPrice) {
            String sa = new String();
            if (text.length() > 2) {
                sa = text.substring(0, text.length() - 2);
                sa += ".";
                sa += text.substring(text.length() - 2);
            } else if (text.length() == 2) {
                sa = "0.";
                sa += text;
            } else if (text.length() == 1) {
                sa = "0.0";
                sa += text;
            } else {
                sa = preText;
            }
            // textView.setText(preText+sa);
            textView.setText(sa);
        } else {
            // textView.setText(preText+text);
            textView.setText(text);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn0) {
            changetext('0');
        } else if (id == R.id.btn00) {
            changetext('0');
            changetext('0');
        } else if (id == R.id.btn1) {
            changetext('1');
        } else if (id == R.id.btn2) {
            changetext('2');
        } else if (id == R.id.btn3) {
            changetext('3');
        } else if (id == R.id.btn4) {
            changetext('4');
        } else if (id == R.id.btn5) {
            changetext('5');
        } else if (id == R.id.btn6) {
            changetext('6');
        } else if (id == R.id.btn7) {
            changetext('7');
        } else if (id == R.id.btn8) {
            changetext('8');
        } else if (id == R.id.btn9) {
            changetext('9');
        } else if (id == R.id.btn_point) {
            changetext('.');
        } else if (id == R.id.btnDel) {
            changetext((char) 0x0f);
        } else if (id == R.id.btn_eliminate || id == R.id.btn_clear) {
            changetext((char) 0x18);
        } else if (id == R.id.button_ok) {
            listener.onClick(bankcard);
        }else if (id == R.id.btn_scan) {
            listener.onClick(scan);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_0) {
            changetext('0');
        } else if (keyCode == KeyEvent.KEYCODE_1) {
            changetext('1');
        } else if (keyCode == KeyEvent.KEYCODE_2) {
            changetext('2');
        } else if (keyCode == KeyEvent.KEYCODE_3) {
            changetext('3');
        } else if (keyCode == KeyEvent.KEYCODE_4) {
            changetext('4');
        } else if (keyCode == KeyEvent.KEYCODE_5) {
            changetext('5');
        } else if (keyCode == KeyEvent.KEYCODE_6) {
            changetext('6');
        } else if (keyCode == KeyEvent.KEYCODE_7) {
            changetext('7');
        } else if (keyCode == KeyEvent.KEYCODE_8) {
            changetext('8');
        } else if (keyCode == KeyEvent.KEYCODE_9) {
            changetext('9');
        } else if (keyCode == KeyEvent.KEYCODE_DEL) {
            changetext((char) 0x0f);
        } else if (keyCode == KeyEvent.KEYCODE_DEL) {
            changetext((char) 0x18);
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * @param c
     * @return 屏幕分辨率
     */
    private int getDisplayMetrics(Context c) {
        DisplayMetrics dm = c.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        return screenWidth;
    }


}
