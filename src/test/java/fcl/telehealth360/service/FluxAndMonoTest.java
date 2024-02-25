package fcl.telehealth360.service;

import org.junit.jupiter.api.Test;

import fcl.telehealth360.service.impl.FluxAndMono;
import reactor.test.StepVerifier;

class FluxAndMonoTest {

  FluxAndMono fluxAndMono = new FluxAndMono();

  @Test
  void fruitsFlux(){
    var fruitsFlux = fluxAndMono.fruitsFlux();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Orange", "Banana")
        .verifyComplete();
  }

  @Test
  void testFruitsMono() {
    var fruitsMono = fluxAndMono.fruitsMono();
    StepVerifier.create(fruitsMono)
        .expectNext("Mango")
        .verifyComplete();
  }

  @Test
  void fruitsFluxMap() {
    var fruitsFlux = fluxAndMono.fruitsFluxMap();
    StepVerifier.create(fruitsFlux)
        .expectNext("MANGO", "ORANGE", "BANANA")
        .verifyComplete();
  }

  @Test
  void fruitsFluxFilter() {
    var fruitsFlux = fluxAndMono.fruitsFluxFilter();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango")
        .verifyComplete();
  }

  @Test
  void fruitsFluxFlatMap() {
    var fruitsFlux = fluxAndMono.fruitsFluxFlatMap();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Orange", "Banana")
        .verifyComplete();
  }

  @Test
  void fruitsFluxFlatMapAsync() {
    var fruitsFlux = fluxAndMono.fruitsFluxFlatMapAsync();
    StepVerifier.create(fruitsFlux)
        .expectNextCount(17)
        .verifyComplete();
  }

  @Test
  void fruitsFluxFlatMapConcat() {
    var fruitsFlux = fluxAndMono.fruitsFluxFlatMapConcat();
    StepVerifier.create(fruitsFlux)
        .expectNextCount(17)
        .verifyComplete();
  }

  @Test
  void fruitsFluxTransformSwitchIfEmpty() {
    var fruitsFlux = fluxAndMono.fruitsFluxTransformSwitchIfEmpty(10);
    StepVerifier.create(fruitsFlux)
        .expectNext( "Mangorooooo")
        .verifyComplete();
  }

  @Test
  void fruitsFluxConcat() {
    var fruitsFlux = fluxAndMono.fruitsFluxConcat();
    StepVerifier.create(fruitsFlux)
        .expectNext( "Mango", "Banana", "Tomato", "Lemon")
        .verifyComplete();
  }

  @Test
  void fruitsFluxMerge() {
    var fruitsFlux = fluxAndMono.fruitsFluxMerge();
    StepVerifier.create(fruitsFlux)
        .expectNext( "Mango", "Banana", "Tomato", "Lemon")
        .verifyComplete();
  }
}