class Logger{
    private static Logger instance;
    private Logger(){
        System.out.println("Logger Created")
    }
    public static Logger getinstance{
        if(instance == null){
            instance = new Logger(); 
        }
        return instance;
    }   
}
