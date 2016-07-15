package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

//add unambiguous imports on the fly
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private TextView quantityTV;
    private int quantity = 0;
    private final int PRICE_PER_CUP = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTV = (TextView)findViewById(R.id.quantity_text_view);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /* WAS
        int quantity =Integer.parseInt(quantityTV.getText().toString());
        displayQuantity(quantity);
        displayPrice(quantity*5);
        */

        // 3B
        CheckBox orderCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = orderCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActivity", "CheckBox checked:"+hasChocolate);

        //3A
        String summary = createOrderSummary(hasWhippedCream, hasChocolate);
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(summary);
    }

    /**
     * 3A: add createOrderSummary
     *  called from submitOrder
     *  take in price
     *  @param addWhippedCream indicated whethere or not used has checked Whipped Cream checkbox
     *  @return message
     */
    public String createOrderSummary(boolean addWhippedCream, boolean addChocolate) {
        String name = "Keith Richards";
        int totalPrice = 0;
        int quantity =Integer.parseInt(quantityTV.getText().toString());
        totalPrice = quantity*PRICE_PER_CUP;

        return  name + "\n"
                +"Add whipped cream? "+addWhippedCream + "\n"
                +"Add chocolate? "+addChocolate + "\n"
                +"Quantity: "+Integer.toString(quantity)+ "\n"
                +"Total: $"+Integer.toString(totalPrice) + "\n"
                +"Thank You\n";
    }

    /**
     * This method displays the given price on the screen.
     * see
     * https://gist.github.com/anonymous/fa134c55a4a43e8d6004
     */
    /*
    * removed in 3A
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number) + "\nThank You!");
    }
    */

    /**
     * This method displays the given quantity value on the screen.
     * Lesson 3A change name
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /*
     * increment or decrement the number of coffees
     * must be public !
     */
    public void increment(View view) {
        quantity++;
        quantityTV.setText(Integer.toString(quantity));
    }

    public void decrement(View view) {
        quantity--;
        quantityTV.setText(Integer.toString(quantity));
    }

    /**
     * From Udacity
     * This method displays the given text on the screen.
     * https://gist.github.com/anonymous/f5feae51009b9f8bc956
     */
    /*
    * removed in 3A
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    */

    /**
     * Lesson 3A add calculatePrice method
     *  @return the price
     */

    private int calculatePrice(int quantity) {
        return quantity*5;
    }



}