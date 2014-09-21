package fxgui;

import Persistance.Member;
import com.dooapp.fxform.FXForm;
import com.dooapp.fxform.builder.FXFormBuilder;
import com.dooapp.fxform.reflection.MultipleBeanSource;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Created by denislavrov on 9/21/14.
 */
public class FXTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Member member = new Member();
        FXForm fxForm = new FXFormBuilder<>()
                .source(new MultipleBeanSource(member, member.getAddress()))
                .exclude("address", "pAddress", "bookLeases", "bookReturns", "itemLeases", "itemReturns", "visits", "currentVisit", "memberId")
                .build();
        fxForm.setStyle("-fx-control-inner-background:  rgb(50, 50, 50);");
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-base: rgb(50,50,50);\n");

        Button btn = new Button("Done");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(member.getFirstname());
            }
        });

        root.getChildren().addAll(fxForm, btn);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
