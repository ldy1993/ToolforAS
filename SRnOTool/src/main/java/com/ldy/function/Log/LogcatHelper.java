package com.ldy.function.Log;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * log日志统计保存
 * 日志最多保存3天，如果内存不足，逐个删除近3+i 天日志
 *
 * @author songjing
 */

public class LogcatHelper {
    private static LogcatHelper instance = null;
    private static String pathLogcat;


    private int order = 0;
    private int mPId;
    //    private String pathLogcat;
    private LogThread mLogThread = null;
    private String mylogfilename = ".log";// 本类输出的日志文件名称
    private int SDCARD_LOG_FILE_SAVE_DAYS = 90;// sd卡中日志文件的最多保存天数
    private static SimpleDateFormat myLogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 日志的输出格式
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyyMMdd");// 日志文件格式
    private static SharedPreferences sharedPreferences; //私有数据
    private static String sn = "";
    ;

    /**
     * 初始化目录
     */
    public void init(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 优先保存到SD卡中
            pathLogcat = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "AllinPayLog";
        } else {
            // 如果SD卡不存在，就保存到本应用的目录下
            pathLogcat = context.getFilesDir().getAbsolutePath() + File.separator + "AllinPayLog";
        }
        File file = new File(pathLogcat);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            if (availableSpace()) {
                delFile(order);
            } else {
                deleteDirectory(pathLogcat);
            }
        }
    }

    /**
     * 内存可用空间大小
     *
     * @return
     */
    private boolean availableSpace() {
        File root = Environment.getRootDirectory();
        StatFs sf = new StatFs(root.getPath());
        long blockSize = sf.getBlockSize();
        long blockCount = sf.getBlockCount();
        long availCount = sf.getAvailableBlocks();
        //总大小
        long totalBlocks = blockSize * blockCount / 1024;
        //可用大小
        long availableBlocks = availCount * blockSize / 1024;
        if (availableBlocks < totalBlocks) {
            return true;
        } else {
            return false;
        }
    }

    public static LogcatHelper getInstance(Context context) {
        sharedPreferences = context.getSharedPreferences("sameDate", Context.MODE_PRIVATE); //私有数据

        if (instance == null) {
            instance = new LogcatHelper(context);
        }
        return instance;
    }

    public static LogcatHelper getInstance(Context context, String string) {
        sharedPreferences = context.getSharedPreferences("sameDate", Context.MODE_PRIVATE); //私有数据
        sn = string;
        if (instance == null) {
            instance = new LogcatHelper(context);
        }
        return instance;
    }

    private LogcatHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("sameDate", Context.MODE_PRIVATE); //私有数据
        init(context);
        mPId = android.os.Process.myPid();
    }

    public void start() {
        if (mLogThread == null)
            mLogThread = new LogThread(String.valueOf(mPId), pathLogcat);
        mLogThread.start();
    }

    public void stop() {
        if (mLogThread != null) {
            mLogThread.stopLogs();
            mLogThread = null;
        }
    }

    private class LogThread extends Thread {
        private Process logcatProc;
        private BufferedReader mReader = null;
        private boolean mRunning = true;
        String cmds = null;
        private String mPID;
        private FileOutputStream out = null;

        public LogThread(String pid, String dir) {
            mPID = pid;
            try {

                out = new FileOutputStream(new File(dir, "today" + mylogfilename), true);
//                out = new FileOutputStream(new File(dir, MyDate.getFileName() + mylogfilename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // cmds = "logcat  | grep \"(" + mPID + ")\"";//打印所有日志信息
            // cmds = "logcat -s way";//打印标签过滤信息
//            cmds = "logcat *:e | grep \"(" + mPID + ")\"";
            cmds = "logcat *:e *:i | grep \"(" + mPID + ")\"";

        }

        public void stopLogs() {
            mRunning = false;
            this.interrupt();
        }

        @Override
        public void run() {
            try {
                logcatProc = Runtime.getRuntime().exec(cmds);
                mReader = new BufferedReader(new InputStreamReader(logcatProc.getInputStream()), 1024);
                String line = null;
                while (mRunning && (line = mReader.readLine()) != null) {
                    if (!availableSpace()) {
                        delFile(order);
                    }
                    if (!mRunning) {
                        break;
                    }
                    if (line.length() == 0) {
                        continue;
                    }
                    if (out != null && line.contains(mPID)) {
                        out.write((MyDate.getDateEN() + " " + sn + "  " + line + "\n").getBytes("UTF-8"));
                    }
                    String tempTime = MyDate.getFileName();
                    if (!isSameDay()) {
                        renameFile(pathLogcat + "/today.log", pathLogcat + "/" + tempTime + mylogfilename);
                        try {
                            out = new FileOutputStream(new File(pathLogcat, "today" + mylogfilename), true);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (logcatProc != null) {
                    logcatProc.destroy();
                    logcatProc = null;
                }
                if (mReader != null) {
                    try {
                        mReader.close();
                        mReader = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    out = null;
                }
                String tempTime = MyDate.getFileName();
                if (!isSameDay()) {
                    renameFile(pathLogcat + "/today.log", pathLogcat + "/" + tempTime + mylogfilename);
                }
            }
        }
    }

    public static class MyDate {
        public static String getFileName() {
            if (sharedPreferences == null) {
                return logfile.format(new Date(System.currentTimeMillis()));
            }
            String sameDate = sharedPreferences.getString("sameDate", "20190522");
            if (!sameDate.equals("20190522")) {
                return sameDate;
            }
            return logfile.format(new Date(System.currentTimeMillis()));
        }

        public static String getDateEN() {
            String date1 = myLogSdf.format(new Date(System.currentTimeMillis()));
            return date1;
        }
    }

    public void delFile(int order) {
        try {
            String needDelFiel = logfile.format(getDateBefore(order));
            needDelFiel = needDelFiel.substring(0, 8);
            int needDelTime = Integer.parseInt(needDelFiel);

            File dirFile = new File(pathLogcat);
            if (dirFile.exists() || dirFile.isDirectory()) {
                File[] files = dirFile.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {

                        String fileName = files[i].getName();
                        try {
                            fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
                            fileName = fileName.substring(0, 8);
                            int filetime = Integer.parseInt(fileName);
                            if (filetime <= needDelTime) {
                                files[i].delete();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e("ldy", "删除日志文件失败，文件时间转换错误");
            e.printStackTrace();

        }
    }

    public static File chooseFile(String date) {
        try {
//            String pathLogcat;
//            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                // 优先保存到SD卡中
//                pathLogcat = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "AllinPayLog";
//            } else {
//                // 如果SD卡不存在，就保存到本应用的目录下
//                pathLogcat = context.getFilesDir().getAbsolutePath() + File.separator + "AllinPayLog";
//            }
            String needDelFiel = date;
            String today = logfile.format(new Date(System.currentTimeMillis()));

            needDelFiel = needDelFiel.substring(0, 8);
            int needDelTime = Integer.parseInt(needDelFiel);

            File dirFile = new File(pathLogcat);
            if (dirFile.exists() || dirFile.isDirectory()) {
                File[] files = dirFile.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        String fileName = files[i].getName();
                        if (fileName.contains("today") && today.equals(date)) {
                            InputStream in=null;
                            OutputStream out=null;
                            File file=null;
                            try {
                                file= new File(pathLogcat, date+".log" );
                                 in = new FileInputStream(new File(pathLogcat, "today.log"));
                                 out = new FileOutputStream(file, true);

                                byte[] buffer = new byte[1024];
                                int byteCount = 0 ;
                                if (!file.exists()) {
                                    //mkdirs()，创建文件目录，包括缺失的父目录，mkdir()只创建没有缺失父目录的目录；
                                    //如创建"/app/small/"，如果本身app文件夹不存在，就用mkdirs()，如果本身就存在，就可以用mkdir()；
                                    file.mkdirs();
                                    Log.e("ldy","文件不存在，重新创建");
                                }
                               while ((byteCount = in.read(buffer)) != -1) {
                                    out.write(buffer, 0, byteCount);
                                }
                                out.flush();
                                out.close();
                                in.close();
                            } catch (Exception e) {
                                Log.e("ldy","文件复制失败");
                                e.printStackTrace();
                                if (out!=null)
                                {
                                    out.close();
                                }
                                if (in!=null)
                                {
                                    in.close();
                                }

                            }
                            return file;
                        }
                        try {
                            fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
                            fileName = fileName.substring(0, 8);
                            int filetime = Integer.parseInt(fileName);
                            if (filetime == needDelTime) {
                                try {
                                    String command = "chmod 777 " + files[i].getAbsolutePath();
                                    Log.i("zyl", "command = " + command);
                                    Runtime runtime = Runtime.getRuntime();
                                    Process proc = runtime.exec(command);
                                } catch (IOException e) {
                                    Log.i("zyl", "chmod fail!!!!");
                                    e.printStackTrace();
                                }
                                return files[i];
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e("ldy", "读取不到日志");
            e.printStackTrace();

        }
        return null;
    }

    public static String readConfigFile(File file) {
        if (!file.exists()) {
            return null;
        }
        BufferedReader reader = null;
        String data = "";
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                data = data + line + "\n";
            }
            data = data.substring(0, data.length() - 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            return URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return data;
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     */
    private Date getDateBefore(int order) {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - SDCARD_LOG_FILE_SAVE_DAYS + order);
        order++;
        if (order == SDCARD_LOG_FILE_SAVE_DAYS) {
            order = 0;
        }
        return now.getTime();
    }

    public void deleteDirectory(String filePath) {
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (dirFile.exists() || dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            //遍历删除文件夹下的所有文件(包括子目录)
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    //删除子文件
                    files[i].delete();
                }
            }
        }
        //删除当前空目录
        dirFile.delete();
    }

    public static boolean isSameDay() {
        if (sharedPreferences == null) {
            return true;
        }
        String sameDate = sharedPreferences.getString("sameDate", "20190522");
        String date = logfile.format(new Date(System.currentTimeMillis()));
        if (sameDate.equals(date)) {
            return true;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("sameDate", date);
        editor.commit();

        return false;
    }

    /**
     * 对文件重命名
     *
     * @param filePath 文件的路径
     */
    public void chageFileName(String filePath, String reName) {
        File file = new File(filePath);
        //前面路径必须一样才能修改成功
        String path = filePath.substring(0, filePath.lastIndexOf("/") + 1) + reName + filePath.substring(filePath.lastIndexOf("."), filePath.length());
        File newFile = new File(path);
        file.renameTo(newFile);
    }

    /**
     * oldPath 和 newPath必须是新旧文件的绝对路径
     */
    private static void renameFile(String oldPath, String newPath) {
        if (TextUtils.isEmpty(oldPath)) {
            return;
        }

        if (TextUtils.isEmpty(newPath)) {
            return;
        }

        File file = new File(oldPath);
        file.renameTo(new File(newPath));
    }
}

