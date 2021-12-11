package bench;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.Student;
import dto.StudentProto;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@Warmup(iterations = 3)
@Measurement(iterations = 10, batchSize = 10)
@State(Scope.Thread)
public class Benchmark {
    static ObjectMapper objectMapper = new ObjectMapper();
    static Student dto = new Student();
    static StudentProto.StudentObject pbDto;
    static byte[] pbBinary;
    static String dtoString;

    @org.openjdk.jmh.annotations.Setup
    public void init() throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        dto = objectMapper.readValue(new File("student_in.json"), Student.class);
        dtoString = new String(Files.readAllBytes(Paths.get("student_in.json")));
        pbDto = StudentProto.StudentObject.parseFrom(new FileInputStream("in.bin"));
        pbBinary = Files.readAllBytes(Paths.get("in.bin"));
    }

    @org.openjdk.jmh.annotations.Benchmark
    public static void JsonSerializeTest(Blackhole blackhole){
        try {
            String result = objectMapper.writeValueAsString(dto);
            blackhole.consume(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    public static void JsonDeserializeTest(Blackhole blackhole)  {
        try {
            Student student = objectMapper.readValue(dtoString, Student.class);
            blackhole.consume(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    public static void ProtobufSerializeTest(Blackhole blackhole) {
        byte[] student = pbDto.toByteArray();
        blackhole.consume(student);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public static void ProtobufDeserializeTest(Blackhole blackhole) {
        try {
            StudentProto.StudentObject student = StudentProto.StudentObject.parseFrom(pbBinary);
            blackhole.consume(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            new Runner(new OptionsBuilder().forks(1).build()).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}

