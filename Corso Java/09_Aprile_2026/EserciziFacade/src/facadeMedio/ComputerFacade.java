package facadeMedio;

public class ComputerFacade {
    private HardDisk hardDisk;
    private SistemaOperativo sistemaOp;
    private Bios bs;

    public ComputerFacade() {
        hardDisk = new HardDisk();
        sistemaOp = new SistemaOperativo();
        bs = new Bios();
    }

    public void accendiComputer() {
        bs.inizializza();
        hardDisk.carica();
        sistemaOp.avvia();
    }

}
