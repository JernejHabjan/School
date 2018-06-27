package si.emp.primes;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Primes extends AsyncTask<Object, Double, Boolean> {
    public interface OnProgressListener {
        void onStarted();
        void onProgress(double progress);
        void onStopped(boolean finished);
    }

    private OnProgressListener onProgressListener = null;
    private boolean keepRunning = false;

    Primes() {
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onProgressListener != null)
            onProgressListener.onStarted();
    }

    @Override
    protected Boolean doInBackground(Object... params) {
        return write((Context)params[0], (String)params[1], (Integer)params[2]);
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        super.onProgressUpdate(values);
        if (onProgressListener != null)
            onProgressListener.onProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Boolean finished) {
        super.onPostExecute(finished);
        if (onProgressListener != null)
            onProgressListener.onStopped(finished);
    }

    public Boolean write(Context context, String filename, Integer limit) {
        String fullpath = context.getApplicationInfo().dataDir + "/" + filename;
        try {
            File file = new File(fullpath);
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(stream);

            keepRunning = true;
            for (int i = 2; i <= limit && keepRunning; i++) {
                if (isPrime(i))
                    writer.write(Integer.toString(i) + " ");
                double percent = 100.0 * (double)i / (double)limit;
                publishProgress(percent);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean finished = keepRunning;
        keepRunning = false;

        return finished;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public void stop() {
        keepRunning = false;
    }
}
