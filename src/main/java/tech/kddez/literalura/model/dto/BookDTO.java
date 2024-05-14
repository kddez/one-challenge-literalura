package tech.kddez.literalura.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(@JsonAlias("title") String title,
                      @JsonAlias("languages") List<String> languages,
                      @JsonAlias("download_count") Integer downloadCount,
                      @JsonAlias("authors") List<AuthorDTO> authors) {
}
