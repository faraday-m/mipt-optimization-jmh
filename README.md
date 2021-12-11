Запуск - Benchmark.java из точки входа.
Вместо записи в файл и чтения из файла использовалось создание строк и байтовых массивов, чтобы измерения не были ограничены скоростью HDD.

Результаты бенчмарка:

|Benchmark                           |Mode  |Cnt        |
|------------------------------------|------|-----------|
Benchmark.JsonDeserializeTest      |thrpt   |10   155202,423 ±  20404,579  ops/s
Benchmark.JsonSerializeTest        |thrpt   |10   331208,342 ±  40676,888  ops/s
Benchmark.ProtobufDeserializeTest  |thrpt   |10   509274,140 ±  48301,023  ops/s
Benchmark.ProtobufSerializeTest    |thrpt   |10  1170343,039 ± 132951,796  ops/s
Benchmark.JsonDeserializeTest       |avgt   |10       ≈ 10⁻⁵                s/op
Benchmark.JsonSerializeTest         |avgt   |10       ≈ 10⁻⁶                s/op
Benchmark.ProtobufDeserializeTest   |avgt   |10       ≈ 10⁻⁶                s/op
Benchmark.ProtobufSerializeTest     |avgt   |10       ≈ 10⁻⁶                s/op

Как видно, Protobuf опережает Jackson более чем в 3 раза как в сериализации, так и в десериализации. Это связано с более оптимальным для машины форматом бинарных данных - не требуется человекопонятность сериализуемого файла и добавление связанных с этим "лишних" символов, известны оффсеты полей и можно получить к ним доступ сразу же, без контекстного анализа. 

В задачах, где требуется высокая нагрузка и не предполагается чтение сохранённых данных человеком, а также где число полей хорошо известно, Protobuf лучший выбор для сериализации объектов, чем Json. В случае, где структура объекта сложная, либо предполагается чтение сериализованных объектов, следует использовать JSON. Его можно использовать также и при отсутствии высокой нагрузки, поскольку DTO классы для protobuf требуется прекомпилировать при помощи protoc. 
