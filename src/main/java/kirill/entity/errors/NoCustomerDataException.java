package kirill.entity.errors;

public class NoCustomerDataException extends CustomerException{
  public NoCustomerDataException() {
    super("No data for your request");
  }

  public NoCustomerDataException(String message) {
    super(message);
  }
}
