package com.example.darksword.shuntingyard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity
{
    private EditText InputET;
    private TextView OutputTV;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.InputET = (EditText)this.findViewById(R.id.InputET);
        this.OutputTV = (TextView)this.findViewById(R.id.OutputTV);
    }
    public void processButtonPressed (View v)
    {
        StringTokenizer st = new StringTokenizer(this.InputET.getText().toString(), "+-*/", true);
        Queue q = new Queue();
        while(st.hasMoreTokens())
        {
            q.enqueue(st.nextToken());
        }


        Queue midPoint = new Queue();
        Queue finish = new Queue();
        Node waiter = q.dequeue();
        Node checker;
        Node combiner;
        if (q.getCount() == 0)
        {
            finish.enqueue(waiter.getPayload());
        }
        while(q.getCount() > 0)
        {
            if (q.getCount() > 0)
            {
                checker = q.dequeue();
            if (checker.getPayload() == "*")
            {
                combiner = q.dequeue();
                int a = Integer.parseInt(waiter.getPayload());
                int b = Integer.parseInt(combiner.getPayload());
                int c = a * b;
                waiter.setPayload("" + c);
            }
            if (checker.getPayload() == "/")
            {
                combiner = q.dequeue();
                int a = Integer.parseInt(waiter.getPayload());
                int b = Integer.parseInt(combiner.getPayload());
                int c = a / b;
                waiter.setPayload("" + c);
            }
            midPoint.enqueue(waiter.getPayload());
            }
        }
        if (midPoint.getCount() == 0)
        {
            finish.enqueue(waiter.getPayload());
        }
        while (midPoint.getCount() > 0)
        {
            checker = q.dequeue();
            if (checker.getPayload() == "+")
            {
                combiner = q.dequeue();
                int a = Integer.parseInt(waiter.getPayload());
                int b = Integer.parseInt(combiner.getPayload());
                int c = a + b;
                waiter.setPayload("" + c);
            }
            if (checker.getPayload() == "-")
            {
                combiner = q.dequeue();
                int a = Integer.parseInt(waiter.getPayload());
                int b = Integer.parseInt(combiner.getPayload());
                int c = a - b;
                waiter.setPayload("" + c);
            }
            finish.enqueue(waiter.getPayload());
        }
        String answer = finish.dequeue().getPayload();
        this.OutputTV.setText(answer);
        /*String answer = "";
        while(finish.getCount() > 0)
        {
            answer = answer + finish.dequeue().getPayload() + ",";
        }
        this.OutputTV.setText(answer);*/

    }
}
