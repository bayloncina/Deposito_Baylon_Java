

class DecoratoreMaiuscolo implements Messaggio {

    private Messaggio messaggioDecorato;

    public DecoratoreMaiuscolo(Messaggio messaggio) {
        this.messaggioDecorato = messaggio;
    }

    @Override
    public String getContenuto() {
        return messaggioDecorato.getContenuto().toUpperCase();
    }
}