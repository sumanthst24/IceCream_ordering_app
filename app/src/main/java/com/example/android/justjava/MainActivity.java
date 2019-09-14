/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int noOfCoffes,finalprice;
    String nameval,res;
    boolean hasWhipped,hasChocolate,hasSprinkles,hasPeanut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = "Total";
        CheckBox WhippedCream = (CheckBox) findViewById(R.id.whippedcream);
        hasWhipped = WhippedCream.isChecked();
        CheckBox Chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = Chocolate.isChecked();
        CheckBox sprinkles=(CheckBox) findViewById(R.id.sprinkles_checkbox);
        hasSprinkles=sprinkles.isChecked();
        CheckBox peanut=(CheckBox) findViewById(R.id.peanut_checkbox);
        hasPeanut=peanut.isChecked();
        EditText text = (EditText) findViewById(R.id.name_field);
        nameval = text.getText().toString();
        if (noOfCoffes == 0) {
            priceMessage = "you ordered nothing";
            displayMessage(priceMessage);
        } else {
            finalprice = calculatePrice(noOfCoffes);
            res = createOrderSummary(finalprice);
            displayMessage(res);
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order summary for "+nameval);
         intent.putExtra(Intent.EXTRA_TEXT,res);
         if (intent.resolveActivity(getPackageManager()) != null) {
             startActivity(intent);
        }
    }

    public void increment(View view){
        noOfCoffes=noOfCoffes+1;
        display(noOfCoffes);
    }
    public void decrement(View view){
        if(noOfCoffes>=1)
        noOfCoffes=noOfCoffes-1;
        display(noOfCoffes);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private int calculatePrice(int quantity)
    {
        int price = quantity * 5;
        if(hasWhipped)
            price=price+(1*noOfCoffes);
        if(hasChocolate)
            price=price+(2*noOfCoffes);
        if(hasPeanut)
            price=price+(3*noOfCoffes);
        if(hasSprinkles)
            price=price+(1*noOfCoffes);
        return price;
    }
    public String createOrderSummary(int price){
        String sendmsg;
        sendmsg="Name :"+nameval;
        sendmsg=sendmsg+"\nQuantity :"+noOfCoffes;
        sendmsg=sendmsg+"\nHas whipped Cream topping "+(hasWhipped);
        sendmsg=sendmsg+"\nHas chocolate topping "+(hasChocolate);
        sendmsg=sendmsg+"\nTotal :$"+price;
        return sendmsg;





    }
}