# Testowanie 4: BDD
Czwarty projekt na zajęcia z testowania oprogramowania.

## Opis aplikacji
Prosta aplikacja do fiszek. K

### Fiszki
Każda fiszka:
- ma dwie strony z czego każda może być w innym języku(język jest definiowany w kolekcji fiszek)
- poziom od 1 do 7. Poziom 1 oznacza, że fiszka nie jest przyswojona a 7 oznacza że jest

### Kolekcje fiszek
Fiszki są pogrupowane w kolekcje. Każda kolekcja posiada:
- Nazwę
- Zdefiniowany język pierwszej strony wszystkich fiszek w kloekcji(np Polski)
- Zdefiniowany język drugiej strony wszystkich fiszek w kloekcji(np Angielski)

### Quiz
Kiedy klient wybierze z której kolekcji chce być pytany, zostanie przepytany z całej kolekcji. 
Gdy dobrze odpowie na pytanie o wartość drugiej strony fiszki, poziom fiszki zostanie podniesiony(chyba, że miał
wartość maksymalną wtedy się nie zmieni). W przypadku w którym udzielona odpowiedź będzie niepoprawna poziom fiszki zostanie 
ustawiony na 1. Zmiany są zapisywane po quizie.

### Planowane wsparcie dla języków
Aplikacja ma wspierać tworzenie fiszek w 3 językach:
- Polski
- Angielski
- Niemiecki

Wspieranie danego języka oznacza, że podczas wprowadzania wprowadzone klawisze tłumaczone są
na odpowiedniki na klawiaturach w danym języku
