package playwrightTest.PlayWright;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class rough {
 public static void main(String[] args) {
	DateFormat dateForm =  new SimpleDateFormat("MM/dd/yyyy");
	Calendar cal = Calendar.getInstance();
	System.out.println(dateForm.format(cal.getTime()));
}
}
