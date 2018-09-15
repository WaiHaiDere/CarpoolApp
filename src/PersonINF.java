public class PersonINF {

    private String _name;
    private String[] _address;

    public String get_name() {
        return _name;
    }

    public String[] get_address() {
        return _address;
    }



    public PersonINF(String name, String[] addressArray){
        _name = name;
        _address = addressArray;

    }

}
