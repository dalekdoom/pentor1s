package com.group4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import static com.group4.Constants.COLS;
import static com.group4.Constants.ROWS;
import static com.group4.Constants.TIMESPAN_NORMAL;
import static com.group4.Constants.width;

/**
 * Created by Tobias on 08/11/2017.
 */

public class GameScreen extends GameLogic implements Screen {
    public static TetrisGame game = null;
    ShapeRenderer renderer;

    private int[][] board=new int[ROWS][COLS];
    private long time;
    private Texture background;
    private SpriteBatch batch;
    private Texture block;
    private Texture aimBlock;
    private int[] next;
    private Texture block1;
    private Stage stage;
    private BitmapFont font;
    private TextButton newGameButton;
    private Skin skin;
    private Texture score1;

    public GameScreen(TetrisGame game) {
        this.game = game;
    }
    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

    }

    @Override
    public void show() {
        super.init();
        board=super.getBoard();
        background = new Texture(Gdx.files.internal("tetris_bg_main2.png"));
        block1 = new Texture(Gdx.files.internal("tetris_block2.png"));
        aimBlock = new Texture(Gdx.files.internal("aim_block.png"));
        batch = new SpriteBatch();
        time=System.currentTimeMillis();
        renderer= new ShapeRenderer();
        font = new BitmapFont();
        createBasicSkin();
        stage = new Stage();
        newGameButton = new TextButton("New game", skin); // Use the initialized skin
        newGameButton.setPosition(6*Gdx.graphics.getWidth()/10, 2*Gdx.graphics.getHeight()/6);
        newGameButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        stage.addActor(newGameButton);
        super.setRun(true);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        aimBlock.dispose();
        block.dispose();
        block1.dispose();
        background.dispose();
        batch.dispose();
        score1.dispose();
        font.dispose();
        renderer.dispose();
    }


    @Override
    public void render(float sth) {
        if (super.getRun()){
            int height = Gdx.graphics.getHeight();
            if (System.currentTimeMillis() - time >= TIMESPAN_NORMAL) {
                if (!super.fall()) {
                    super.removeLine();
                    if (!super.init()) {
                        super.reset();
                    }
                }
                time = System.currentTimeMillis();
            }
            Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(background, 0, 0, width, height);
            batch.end();
            drawShape(height);
            drawNext(height);
            drawText(height);
            if (super.getFullLines() > 0) {
                score1 = new Texture(Gdx.files.internal("score_" + toString(1) + ".png"));
                batch.begin();
                batch.draw(score1, width / 2 - 3 / 2 * (width / COLS), height / 2 - (height / ROWS), 3 * (width / COLS), 2 * (height / ROWS));
                batch.end();
            }
            stage.act();
            stage.draw();
        }
    }

    private void drawText(int height) {
        batch.begin();
        font.setColor(Color.BLACK);
        font.getData().setScale(2f);
        font.draw(batch, "Score", Math.round(1.5*width),(ROWS-7)*(height / ROWS));
        batch.end();
        batch.begin();
        font.getData().setScale(2f);
        font.draw(batch, super.toString(getScore()), Math.round(1.5*width),(ROWS-7)*(height / ROWS)-50);
        batch.end();
    }

    private void drawNext(int height) {
        next=super.displayNext();
        block1 = new Texture(Gdx.files.internal("tetris_block"+toString(next[0])+".png"));
        batch.begin();
        batch.draw(block1,Math.round(1.5*width), (ROWS-2)*(height / ROWS), (width / COLS), (height / ROWS));
        batch.end();
        for(int i=1;i<next.length;i+=2){
            batch.begin();
            batch.draw(block1,Math.round(1.5*width)+next[i+1] * (width / COLS), (ROWS-2-next[i])*(height / ROWS), (width / COLS), (height / ROWS));
            batch.end();
        }
    }

    private void drawShape(int height) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] != 0&& board[i][j] != -1) {
                    block = new Texture(Gdx.files.internal("tetris_block"+toString(board[i][j])+".png"));
                    batch.begin();
                    batch.draw(block,j * (width / COLS), (ROWS-1-i) * (height / ROWS), (width / COLS), (height / ROWS));
                    batch.end();
                }
                else if(board[i][j] == -1){
                    batch.begin();
                    batch.draw(aimBlock,j * (width / COLS), (ROWS-1-i) * (height / ROWS), (width / COLS), (height / ROWS));
                    batch.end();
                }
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
