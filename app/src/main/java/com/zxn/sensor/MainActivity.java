package com.zxn.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tv_x)
    TextView tvX;
    @ViewInject(R.id.tv_y)
    TextView tvY;
    @ViewInject(R.id.tv_z)
    TextView tvZ;

    @ViewInject(R.id.tv_orientation_x)
    TextView tv_orientation_x;
    @ViewInject(R.id.tv_orientation_y)
    TextView tv_orientation_y;
    @ViewInject(R.id.tv_orientation_z)
    TextView tv_orientation_z;

    @ViewInject(R.id.tv_light)
    TextView tv_light;
    @ViewInject(R.id.tv_pressure)
    TextView tv_pressure;
    @ViewInject(R.id.tv_temperature)
    TextView tv_temperature;

    @ViewInject(R.id.tv_gyroscope_x)
    TextView tv_gyroscope_x;
    @ViewInject(R.id.tv_gyroscope_y)
    TextView tv_gyroscope_y;
    @ViewInject(R.id.tv_gyroscope_z)
    TextView tv_gyroscope_z;

    @ViewInject(R.id.tv_gyroscope_x)
    TextView tv_gravity_x;
    @ViewInject(R.id.tv_gyroscope_y)
    TextView tv_gravity_y;
    @ViewInject(R.id.tv_gyroscope_z)
    TextView tv_gravity_z;

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //获取传感器管理者对象
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //通过传感器管理者对象,获取指定类型的传感器,例如加速传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //获取方向传感器
    }

    private String TAG = getClass().getSimpleName();
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //在监听回调中获取具体的数据,根据数据执行具体的逻辑.
            //传感器变化监听
            int type = event.sensor.getType();
            float[] values = event.values;
            //x方向的值
            float x = values[0];
            //y方向的值
            float y = values[1];
            //z方向的值
            float z = values[2];
            switch (type) {
                case Sensor.TYPE_ACCELEROMETER://加速传感器返回数据
                    tvX.setText("x方向的加速度:".concat(String.valueOf(x)));
                    tvY.setText("y方向的加速度:".concat(String.valueOf(y)));
                    tvZ.setText("z方向的加速度:".concat(String.valueOf(z)));
                    break;
                case Sensor.TYPE_ORIENTATION://方向传感器返回数据,过时
                    String xText = "x方向的旋转角度:".concat(String.valueOf(x));
                    tv_orientation_x.setText(xText);
                    String yText = "y方向的旋转角度:".concat(String.valueOf(y));
                    tv_orientation_y.setText(yText);
                    String zText = "z方向的旋转角度:".concat(String.valueOf(z));
                    tv_orientation_z.setText(zText);
                    Log.i(TAG, "onSensorChanged: xText--->" + xText);
                    Log.i(TAG, "onSensorChanged: yText--->" + yText);
                    Log.i(TAG, "onSensorChanged: zText--->" + zText);
                    break;
                case Sensor.TYPE_GYROSCOPE://陀螺仪传感器返回数据,过时
                    String xText1 = "x方向的陀螺仪旋转角速度:".concat(String.valueOf(x));
                    tv_gyroscope_x.setText(xText1);
                    String yText1 = "y方向的陀螺仪旋转角速度:".concat(String.valueOf(y));
                    tv_gyroscope_y.setText(yText1);
                    String zText1 = "z方向的陀螺仪旋转角速度:".concat(String.valueOf(z));
                    tv_gyroscope_z.setText(zText1);
                    Log.i(TAG, "onSensorChanged: xText--->" + xText1);
                    Log.i(TAG, "onSensorChanged: yText--->" + yText1);
                    Log.i(TAG, "onSensorChanged: zText--->" + zText1);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD://磁场传感器返回数据
                   /* String xText2 = "x方向的磁场:".concat(String.valueOf(x));
                    tv_orientation_x.setText(xText2);
                    String yText2 = "y方向的磁场:".concat(String.valueOf(y));
                    tv_orientation_y.setText(yText2);
                    String zText2 = "z方向的磁场:".concat(String.valueOf(z));
                    tv_orientation_z.setText(zText2);
                    Log.i(TAG, "onSensorChanged: xText--->" + xText2);
                    Log.i(TAG, "onSensorChanged: yText--->" + yText2);
                    Log.i(TAG, "onSensorChanged: zText--->" + zText2);*/
                    break;
                case Sensor.TYPE_GRAVITY://重力传感器返回数据
                    String xGravity = "x方向的重力:".concat(String.valueOf(x));
                    tv_gravity_x.setText(xGravity);
                    String yGravity = "y方向的重力:".concat(String.valueOf(y));
                    tv_gravity_y.setText(yGravity);
                    String zGravity = "z方向的重力:".concat(String.valueOf(z));
                    tv_gravity_z.setText(zGravity);
                    Log.i(TAG, "onSensorChanged: xText--->" + xGravity);
                    Log.i(TAG, "onSensorChanged: yText--->" + yGravity);
                    Log.i(TAG, "onSensorChanged: zText--->" + zGravity);
                    break;
                case Sensor.TYPE_LIGHT://光传感器返回数据--ok
                    String xText2 = "光:".concat(String.valueOf(x));
                    tv_light.setText(xText2);
                    Log.i(TAG, "onSensorChanged: xText--->" + xText2);
                    break;
                case Sensor.TYPE_PRESSURE://压力传感器返回数据---no
                    String xPressure = "压力:".concat(String.valueOf(x));
                    tv_pressure.setText(xPressure);
                    Log.i(TAG, "onSensorChanged: xText--->" + xPressure);
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION://线性加速度---no
                    String xLinear = "x方向的线性加速度:".concat(String.valueOf(x));
                    tv_orientation_x.setText(xLinear);
                    String yLinear = "y方向的线性加速度:".concat(String.valueOf(y));
                    tv_orientation_y.setText(yLinear);
                    String zLinear = "z方向的线性加速度:".concat(String.valueOf(z));
                    tv_orientation_z.setText(zLinear);
                    Log.i(TAG, "onSensorChanged: xText--->" + xLinear);
                    Log.i(TAG, "onSensorChanged: yText--->" + yLinear);
                    Log.i(TAG, "onSensorChanged: zText--->" + zLinear);
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE://温度---no
                    String xTemperature = "温度:".concat(String.valueOf(x));
                    tv_temperature.setText(xTemperature);
                    Log.i(TAG, "onSensorChanged: xText--->" + xTemperature);
                    break;
            }

        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //精度变化的监听回调
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //使用传感器管理者对象注册监听
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
        //注册方向传感器
        /*sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                        SensorManager.SENSOR_DELAY_GAME);*/
        /*sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(SensorManager.getOrientation()),
                        SensorManager.SENSOR_DELAY_GAME);*/
        //为陀螺仪传感器注册监听器
        sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                        SensorManager.SENSOR_DELAY_GAME);
        // 为磁场传感器注册监听器
       /* sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                        SensorManager.SENSOR_DELAY_GAME);*/
        // 重力传感器注册监听器
        sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                        SensorManager.SENSOR_DELAY_GAME);
        // 光传感器注册监听器
        sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                        SensorManager.SENSOR_DELAY_GAME);
        //压力传感器
        sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                        SensorManager.SENSOR_DELAY_GAME);
        //线性加速度传感器
        sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                        SensorManager.SENSOR_DELAY_GAME);
        //温度传感器
        sensorManager
                .registerListener(listener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                        SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //页面不可见的时候,注销监听
        //注销掉传感器的监听
        sensorManager.unregisterListener(listener);
    }


    @Event(value = {R.id.tv_title})
    private void click(View view) {
        x.Ext.init(getApplication());
        x.Ext.setDebug(true);
        Toast.makeText(this, "11111111", Toast.LENGTH_SHORT).show();
        String uri = "http://192.168.1.166/ERP.WebApi.Warehouse/Api/Login/Login";
        RequestParams requestParams
                = new RequestParams(uri);
        requestParams.addBodyParameter("User_Name", "kg");
        requestParams.addBodyParameter(" Password", "1234");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "onSuccess: --->" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
