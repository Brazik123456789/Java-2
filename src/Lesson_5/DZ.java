package Lesson_5;

public class DZ {

    static final int size = 10000000;
    static final int h = size / 2;


    public float[] glue(float[] arr){       //  прописал функцию чтобы не прописывать этот цикл трижды
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        };
        return arr;
    }


    public void method1() {     //  1 метод

        float[] arr = new float[size];
        for (float a : arr) {
            a = 1;
        }

        long a = System.currentTimeMillis();
        arr = glue(arr);
        long time = System.currentTimeMillis() - a;
        System.out.println("Время первого потока " + time);

    }



    public synchronized void method2() {        //  Я сейчас поставил синхронизацию и думаю, что и без нее вроде как все должно хорошо работать, ведь мы в потоках работаем с отдельными подмассивами
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        for (float a : arr) {
            a = 1;
        }

        long a = System.currentTimeMillis();

        //делим массив на 2
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        //создаем поток для 1-го подмассива
        new Thread(new Runnable() {
            @Override
            public void run() {
                float[] arr1 = glue(a1);
                System.arraycopy(arr1, 0, a1, 0, arr1.length);
            }
        }).start();

        //создаем поток для 2-го подмассива
        new Thread(new Runnable() {
            @Override
            public void run() {
                float[] arr2 = glue(a2);
                System.arraycopy(arr2, 0, a1, 0, arr2.length);
            }
        }).start();

        System.arraycopy(a1,0,arr,0,h);
        System.arraycopy(a2,0,arr,h,h);
        long time = System.currentTimeMillis() - a;
        System.out.println("Время второго потока " + time);
    }


    public static void main(String[] args) {

        DZ dz = new DZ();
        dz.method1();
        dz.method2();

    }

}