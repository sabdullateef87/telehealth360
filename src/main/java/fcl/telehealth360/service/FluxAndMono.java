package fcl.telehealth360.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMono {
  public Flux<String> fruitsFlux(){
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"));
  }

  public Flux<String> fruitsFluxMap(){
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .map(String::toUpperCase);
  }

  public Flux<String> fruitsFluxFlatMap(){
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .flatMap(Flux::just);
  }


  public Flux<String> fruitsFluxFlatMapAsync(){
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .flatMap(item -> Flux.just(item.split("")))
        .delayElements(Duration.ofMillis(new Random().nextInt(1000))).log();
  }

  public Flux<String> fruitsFluxFlatMapConcat(){
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .concatMap(item -> Flux.just(item.split("")))
        .delayElements(Duration.ofMillis(new Random().nextInt(1000))).log();
  }

  private Function<Flux<String>, Flux<String>> filterCondition(int number){
    return data -> data.filter(s -> s.length() > number);
  }

  public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number ){
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .transform(filterCondition(10))
        .switchIfEmpty(Flux.just("Pineapple", "Mangorooooo"))
        .transform(filterCondition(10))
        .log();
  }

  public Flux<String> fruitsFluxFilter(){
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .filter(item -> item.startsWith("M"))
        .defaultIfEmpty("");
  }

  public Flux<String> fruitsFluxConcat(){
    var fruits  = Flux.just("Mango","Banana");
    var veggies = Flux.just("Tomato", "Lemon");

    return Flux.concat(fruits, veggies);
  }

  public Flux<String> fruitsFluxMerge(){
    var fruits  = Flux.just("Mango","Banana").delayElements(Duration.ofMillis(50));
    var veggies = Flux.just("Tomato", "Lemon").delayElements(Duration.ofMillis(40));

    return Flux.concat(fruits, veggies);
  }
  public Mono<String> fruitsMono(){
    return Mono.just("Mango");
  }

  public static void main(String[] args){
    FluxAndMono fluxAndMono = new FluxAndMono();
    fluxAndMono.fruitsFlux().subscribe(System.out::println);
//    fluxAndMono.fruitsMono().subscribe(System.out::println);
  }
}
