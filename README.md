# Internal Nested DSL: Java :  LDAP Search DSL
## Allgemeines
Inspiriert von einem Blog Eintrag von Benji Weber (@benjiman), packte mich das Interesse seine Ideen mal ausprobieren und zu verstehen, was er dort macht. Dazu kam noch eine Situation innerhalb eines Projektes, dass LDAP- Search Queries irgendwie schöner hin zu kriegen. Daraus entstanden ist nun ein Beispiel auf Basis von Benji Webers "Serialized Lambda".

Since i heard the talk at JavaOne14 from @benjiman about java 8, i was inspired by his way of doing things in the lambda world. so i planned for myself that, if there is any use-case, which could fit, i will play with these serialized lambda things... it is 2017 and time has come.
While working upon a project where ldap has been heavily used, i saw colleagues of mine working with "String concats" to build ldap search queries. to defend them...  the search query build process is abstracted in some way.
 But in these situations, i'm feeling, that there has to be a better, more clearer, beautiful way of doing that. A way someone does not start to chitter, when he sees this or does not want to take  a shower after writing the code.     
## Serialized Lambdaa
In Java 8 sind  nun Lambdas Bestandteil der Sprache geworden, welches zunächst die Schreibweisen vereinfacht und von den anonymen Klassen einen Ausweg bietet. Diese Syntax macht sich nun Benji Weber zu nutzen. Denn "Serialized Lambda" beschreibt einen Weg den Variablen Namen eines Lambdas als Stilmittel zu nutzen und auszuwerten. Im Mittelpunkt steht dabei nicht die Methode an sich, sondern die Notation. 
Das angehangene Beispiel zeigt das Endergebnis. Die Schachtelung der Methoden liest sich dabei ähnlich dem Endprodukt, erlauben zudem eine fehlerfreiere Nutzung durch z.B. die Unterstützung des Compilers für die Klammersetzung


Nun ist die erste Fragestellung natürlich , wie man an den Parameter Namen kommt. Das Herzstück des ganzen ist die Klasse SerializedLambda und damit der Option die Text-Representation des Lambdas aus zu lesen. Vorraussetzung dafür ist natürlich, dass die Namen im Kompilat erhalten bleiben. Dies passiert z.B mit dem Compiler Flag "parameters



Java 8 introduced lambdas, generating the chance to simplify the notation and getting rid of anonymous inner classes. the syntax is the starting point for the trick benji presented at JavaOne. He used it as kind of stylistic device for creating internal java dsls. He showed a way to retrieve an instance of a SerializedLambda, which has the opportunity  to access the parameter names, which does make sense, if you compile with the "parameters" flag (preserves these) . applied to the ldap search query use-case, i was able to do something like this.


```javaa

    String search =  query(and(attr(test -> "value"), attr(test2 -> "value2"))).asString();
``` 

```javaa

    "(&(test=value)(test2=value2))"
```


 
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
 
 ```javaa
 
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


the idea behind this kind of dsl is to inherit all the methods defining your dsl. these methods actually build an AST-like object of your defined language. as shown, the and operation returns an And -Object implementing several methods, allowing to act as general tokens, which only has this asString-method.
 while playing around, i realized that the html example of benji is definitely a bit better than mine. his design only allows that a token has defined type elements inside his "token"(attribute or tag). my design is based around the assumption, that every operation has attributes or further operations in any order. this is the reason of this var-arg parameter construct. to sum it up, i had to wrap the actual Object, to place the generic var-arg. but this also introduced the need in having this attr-method. it would be so nice to get around this, but i haven't found anything better. 
 

At the end, i learned a lot and received a new perspective of OO in defining and using decorators like this. thank´s benji !!       
## Quelle/Sourcesn
* Benji Weber : Serialized Lambda (http://benjiweber.co.uk/blog/2015/08/17/lambda-parameter-names-with-reflection/)
* Benji Weber : HTML in Java (http://benjiweber.co.uk/blog/2015/08/21/html-in-java/)
* Benji Weber : Forward-Interface-Pattern (http://benjiweber.co.uk/blog/2014/04/14/java-forwarding-interface-pattern/)


