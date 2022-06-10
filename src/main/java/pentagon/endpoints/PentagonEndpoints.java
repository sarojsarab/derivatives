package main.java.pentagon.endpoints;


public class PentagonEndpoints {

    private PentagonEndpoints endpoints = null;

    //    This method will provide an instance of Endpoint class
    public synchronized PentagonEndpoints getPentagonEndpoints() {
        if (endpoints == null) {
            endpoints = new PentagonEndpoints();
        }
        return endpoints;
    }

    //OpenOrder endpoints
}
