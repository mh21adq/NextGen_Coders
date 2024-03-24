package com.example.next.ui.examples;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.next.R;

public class CodeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_display);

        // Enable the back button in the ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Example Code"); // Set the action bar title
        }

        String exampleName = getIntent().getStringExtra("example_name");

        TextView titleJava = findViewById(R.id.titleJava);
        TextView javaCode = findViewById(R.id.javaCode);
        TextView titlePython = findViewById(R.id.titlePython);
        TextView pythonCode = findViewById(R.id.pythonCode);
        TextView titleCpp = findViewById(R.id.titleCpp);
        TextView cppCode = findViewById(R.id.cppCode);
        TextView titleJs = findViewById(R.id.titleJs);
        TextView jsCode = findViewById(R.id.jsCode);

        // Set the headings for each code section
        switch (exampleName) {
            case "hello_world":
                titleJava.setText("Java");
                javaCode.setText("public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}");
                titlePython.setText("Python");
                pythonCode.setText("print(\"Hello, World!\")");
                titleCpp.setText("C++");
                cppCode.setText("#include <iostream>\nint main() {\n    std::cout << \"Hello, World!\" << std::endl;\n    return 0;\n}");
                titleJs.setText("JavaScript");
                jsCode.setText("console.log(\"Hello, World!\");");
                break;
            case "add_two_integers":
                titleJava.setText("Java");
                javaCode.setText("public class AddTwoIntegers {\n    public static void main(String[] args) {\n        int first = 10;\n        int second = 20;\n        System.out.println(\"The sum is: \" + (first + second));\n    }\n}");
                titlePython.setText("Python");
                pythonCode.setText("first = 10\nsecond = 20\nprint('The sum is:', first + second)");
                titleCpp.setText("C++");
                cppCode.setText("#include <iostream>\nint main() {\n    int first = 10;\n    int second = 20;\n    std::cout << \"The sum is: \" << (first + second) << std::endl;\n    return 0;\n}");
                titleJs.setText("JavaScript");
                jsCode.setText("let first = 10;\nlet second = 20;\nconsole.log('The sum is:', first + second);");
                break;
            // Add cases for other examples as needed
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // This will handle the back action
        return true;
    }
}
