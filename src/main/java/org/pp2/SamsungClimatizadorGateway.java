package org.pp2;

import org.pp2.mock.AireAcondicionadoSamsung;

public class SamsungClimatizadorGateway implements ClimatizadorGateway {

    private final AireAcondicionadoSamsung aireAcondicionadoSamsung;

    public SamsungClimatizadorGateway(AireAcondicionadoSamsung aireAcondicionadoSamsung) {
        this.aireAcondicionadoSamsung = aireAcondicionadoSamsung;
    }

    @Override
    public void apagar() {
        aireAcondicionadoSamsung.apagar();
    }

    @Override
    public void encender() {
        aireAcondicionadoSamsung.encender();
    }
}
