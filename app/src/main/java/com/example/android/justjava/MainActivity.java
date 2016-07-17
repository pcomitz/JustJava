package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//add unambiguous imports on the fly
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private TextView quantityTV;
    private int quantity = 1;
    private final int PRICE_PER_CUP = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTV = (TextView)findViewById(R.id.quantity_text_view);
    }

    /**
     * This method is called when the order button is clicked.
     * * Select
     * * Ctrl /
     * * Comments out all selected lines
     */
    public void submitOrder(View view) {
        /* WAS
        int quantity =Integer.parseInt(quantityTV.getText().toString());
        displayQuantity(quantity);
        displayPrice(quantity*5);
        */

        // 3B
        //whipped cream
        CheckBox orderCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = orderCheckBox.isChecked();

        //chocolcate
        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActivity", "CheckBox checked:"+hasChocolate);

        //get name
        EditText nameEditText = (EditText)findViewById(R.id.nameEditText);
        String name = nameEditText.getText().toString();

        //3A
        String summary = createOrderSummary(hasWhippedCream, hasChocolate,name);
        //3B - since emailing order, no need to display summary in app anymore
        //TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        //orderSummaryTextView.setText(summary);


        //3B - email the order
        String[] addresses = {"pcomitz@yahoo.com", "mike@sloan.com"};
        //open the email app with an intent
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * 3A: add createOrderSummary
     *  called from submitOrder
     *  take in price
     *  @param addWhippedCream indicated whethere or not used has checked Whipped Cream checkbox
     *  @return message
     */
    public String createOrderSummary(boolean addWhippedCream, boolean addChocolate,
                                     String name) {
        int totalPrice = 0;
        int quantity =Integer.parseInt(quantityTV.getText().toString());

        if(quantity < 0) {
            Toast.makeText(this, "You must order at least 1 cup of coffee", Toast.LENGTH_LONG).show();
            return("Please try again");
        } else if(quantity > 100) {
            Toast.makeText(this, "You cannot order more than 100 cups", Toast.LENGTH_LONG) .show();
            return("Please try again");
        }

        //TODO - add calculate price method
        totalPrice = quantity*PRICE_PER_CUP;

        //add $1 per cup if whipped cream
        if(addWhippedCream)
            totalPrice += quantity*1;

        //add $2 per cup if chocolate
        if(addChocolate)
            totalPrice += quantity*2;

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
        //don't allow quantity to be incremented > 100
        if(quantity == 100) {
            Toast.makeText(this, "You cannot order more than 100 cups", Toast.LENGTH_LONG) .show();
            return;
        }
        quantity++;
        quantityTV.setText(Integer.toString(quantity));
    }

    public void decrement(View view) {
        //don't allow quantity to be decremented to 0
        if(quantity == 1) {
            Toast.makeText(this, "You must order at least 1 cup of coffee", Toast.LENGTH_LONG).show();
            return;
        }

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

} //~