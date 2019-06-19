package ru.alekseyld.greenhouseapp.model

import org.junit.Assert
import org.junit.Test
import ru.alekseyld.greenhouseapp.repository.IEspRepository

class EspRequestTest {
    @Test
    fun equals() {

        val espRequest = EspRequest(
            requestType = EspRequest.Type.SetState,
            param = listOf(IEspRepository.Node.Fan, IEspRepository.State.On),
            disposableHandler = {}
        )

        val espRequest1 = EspRequest(
            requestType = EspRequest.Type.SetState,
            param = listOf(IEspRepository.Node.Fan, IEspRepository.State.On),
            disposableHandler = {}
        )

        Assert.assertTrue(espRequest.equals(espRequest1))
        Assert.assertTrue(espRequest1.equals(espRequest))
    }
}