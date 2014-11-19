package persistance;


import lombok.*;
import org.apache.solr.analysis.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;
import persistance.base.LeasableItem;
import util.DBIO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"author", "image"})
@Entity
@Indexed
@AnalyzerDefs({
        @AnalyzerDef(name="projectionAnalyzer",
                tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class)),

        @AnalyzerDef(name = "TokenizingLower",
                tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = PatternReplaceFilterFactory.class,
                                params = {
                                        @Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
                                        @Parameter(name = "replacement", value = " "),
                                        @Parameter(name = "replace", value = "all") }),
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = StopFilterFactory.class),
                        @TokenFilterDef(factory = SnowballPorterFilterFactory.class,
                                params = {@Parameter(name = "language", value = "English")})
                }),

        @AnalyzerDef(name="autoEdge", // analyzer for auto suggestions
                tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = StopFilterFactory.class),
                        @TokenFilterDef(factory = EdgeNGramFilterFactory.class,
                                params = {
                                        @Parameter(name = "minGramSize", value = "3"), // prediction lower boundary
                                        @Parameter(name = "maxGramSize", value = "50") // prediction upper boundary
                                })
                }),
        @AnalyzerDef(name="autoNgram", // analyzer for auto suggestions
                tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = WordDelimiterFilterFactory.class),
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = NGramFilterFactory.class,
                                params = {
                                        @Parameter(name = "minGramSize", value = "3"), // prediction lower boundary
                                        @Parameter(name = "maxGramSize", value = "5")
                                }),
                        @TokenFilterDef(factory = PatternReplaceFilterFactory.class,
                                params = {
                                        @Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
                                        @Parameter(name = "replacement", value = " "),
                                        @Parameter(name = "replace", value = "all") })

                })

})
public class Book extends LeasableItem<Book> {
    /*
    Annotation Summary:
    @Transient -                Fields that are not persisted.
    @Entity -                   Classes that will be persisted.
    @Indexed -                  Tells Search to index this entity.
    @IndexedEmbedded -          Tells Search to index embedded Entity.
    @ElementCollection -        Used to store Collections in a linked table.
    @Column -                   Establishes relationship of field with database field.
    @Field -                    Tells search that this Field needs to be indexed.
    @Temporal -                 Data that is time or date or timestamp.
    @Id -                       Primary key.
    @GeneratedValue -           Automatic surrogate primary key generation.
    @[One|Many]To[One|Many] -   Relationship Types.
     */

    @NotNull
    @Fields({
            @Field(name = "store_title", analyzer = @Analyzer(definition = "projectionAnalyzer"), store = Store.COMPRESS),
            @Field(name = "title", analyzer = @Analyzer(definition = "TokenizingLower")),
            @Field(name = "edgeTitle", analyzer = @Analyzer(definition = "autoEdge")),
            @Field(name = "ngramTitle", analyzer = @Analyzer(definition = "autoNgram")),
    })
    private String title;
    @Field(analyze = Analyze.NO)
    @DateBridge(resolution = Resolution.YEAR)
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Field(analyze = Analyze.NO)
    @NumericField
    private Integer pages;
    @Field
    private String publisher;
    @Field
    @Analyzer(definition = "TokenizingLower")
    private String description;
    private Long barcode;
    @Field
    private String isbn;
    @Field
    @NotNull
    private int edition = 1;
    @Field(analyze = Analyze.NO)
    private String category;
    @Field(analyze = Analyze.NO)
    @NumericField
    private Double rating;
    @Field
    @Analyzer(definition = "TokenizingLower")
    private String summary;
    @Embedded
    private DBImage image = new DBImage();
    @IndexedEmbedded
    @ElementCollection(fetch = FetchType.EAGER) // Specify this if you want Hibernate to fetch linked data.
    private Collection<String> author;


}
