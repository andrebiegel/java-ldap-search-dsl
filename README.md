# Internal Nested DSL: Java :  LDAP Search DSL
## Allgemeines
Inspiriert von einem Blog Eintrag von Benji Weber, packte mich das Interesse seine Ideen mal ausprobieren und zu verstehen, was er dort macht. Dazu kam noch eine Situation innerhalb eines Projektes, dass LDAP- Search Queries irgendwie schöner hin zu kriegen. Daraus entstanden ist nun ein Beispiel auf Basis von Benji Webers "Serialized Lambda".

## Serialized Lambda
In Java 8 sind  nun Lambdas Bestandteil der Sprache geworden, welches zunächst die Schreibweisen vereinfacht und von den anonymen Klassen einen Ausweg bietet. Diese Syntax macht sich nun Benji Weber zu nutzen. Denn "Serialized Lambda" beschreibt einen Weg den Variablen Namen eines Lambdas als Stilmittel zu nutzen und auszuwerten. Im Mittelpunkt steht dabei nicht die Methode an sich, sondern die Notation. 

```java

    String search =  query(and(attr(test -> "value"), attr(test2 -> "value2"))).asString();
``` 

```java

    "(&(test=value)(test2=value2))"
```

Das gezeigte Beispiel zeigt das Endergebnis. Die Schachtelung der Methoden liest sich dabei ähnlich dem Endprodukt, erlauben zudem eine fehlerfreiere Nutzung durch z.B. die Unterstützung des Compilers für die Klammersetzung.

Nun ist die erste Fragestellung natürlich , wie man an den Parameter Namen kommt. Das Herzstück des ganzen ist die Klasse SerializedLambda und damit der Option die Text-Representation des Lambdas aus zu lesen. Vorraussetzung dafür ist natürlich, dass die Namen im Kompilat erhalten bleiben. Dies passiert z.B mit dem Compiler Flag "parameters"
 
 ```
 
    default SerializedLambda serialized() {
		try {
			Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
			replaceMethod.setAccessible(true);
			return (SerializedLambda) replaceMethod.invoke(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
 ```
 ```
 
    default Method method() {
		SerializedLambda lambda = serialized();
		Class<?> containingClass = getContainingClass();
		return Arrays.asList(containingClass.getDeclaredMethods()).stream()
				.filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName())).findFirst()
				.orElseThrow(UnableToGuessMethodException::new);
	}
 ``` 
 ## Nested DSLs
 
 Die Idee hinter Nested DSLs sind vererbte Funktionen, welche die Parameter und Rückgabetypen geschickt einsetzen, dass sie für den Nutzer als DSL interpretiert werden. 
 
 ```java
 
    default TypedInstance<And> and(TypedInstance<? extends Token>... children) {
 	
		return ops(new And() {
		
			@Override
			
			public String asString() {
			
				return operation(AND);
			}
			
			@Override
			public List<? extends Token> children() {
				return Arrays.asList(children).stream().map(TypedInstance::instance).collect(Collectors.toList());
			}
		});
	}
 
 ```
  Am Beispiel von Benji Weber erkennt man schnell, dass es einfacher ist, keine generischen Typen als Parameter zu haben. Dabei kommt ihm natürlich sein HTML Beispiel zur Hilfe. In meinem Beispiel ist es jedoch der Fall, dass ein Operator von verschiedenen Typen (Operatoren und Literalen) bedient werden kann, sodass ich hier mit Generics rum probiert habe, bis ich die für mich beste Option gefunden habe. Kennt jemand eine Variante, wo ich die attr-Funktion und damit die Kapselung mit TypedInstances umgehen kann?   

Die Methoden selbst sind alle als Default-Methoden in Interfaces definiert. Dies ermöglicht die Nutzung von Mehrfachvererbung, welche bei normalen Klassen in Java nicht zur Verfügung steht. 
Diese Mehrfachvererbung wiederum eröffnet, eine für mich noch nicht so erkannte OO-Perspektive. Denn dadurch kann man wunderbar die  Komponenten der DSL verknüpfen. So eine derartige Implementierung eines Decorator nennt er Forwarding-Interface-Pattern. Allerdings wüsste ich nicht warum es einen neuen Namen benötigt.   
 
## Quellen
* Benji Weber : Serialized Lambda (http://benjiweber.co.uk/blog/2015/08/17/lambda-parameter-names-with-reflection/)
* Benji Weber : HTML in Java (http://benjiweber.co.uk/blog/2015/08/21/html-in-java/)
* Benji Weber : Forward-Interface-Pattern (http://benjiweber.co.uk/blog/2014/04/14/java-forwarding-interface-pattern/)


