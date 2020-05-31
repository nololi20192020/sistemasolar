package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Pantalla implements Screen {
    private PerspectiveCamera camara3d;
    private ModelBatch modelBatch;

    private Array<ModelInstance> elementos_sistema_solar;
    private ModelInstance mercurio;
    private ModelInstance sol;
    private ModelInstance venus;
    private ModelInstance tierra;
    private ModelInstance marte;

    private Environment environment;
    private CameraInputController camController;


    private Vector3[] posicions = {(new Vector3(-0.5f, 0.00f, 0.0f)), (new Vector3(-0.8f, 0f, 0f)),
            (new Vector3(-1.0f, 0f, 0f)),(new Vector3(-1.2f, 0f, 0f))};


    public Pantalla() {
        //inicializar array
        elementos_sistema_solar = new Array<>();

        camara3d = new PerspectiveCamera();
        camController = new CameraInputController(camara3d);
        Gdx.input.setInputProcessor(camController);

        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.3f, 0.5f, 0.5f, 0.5f));
        environment.add(new DirectionalLight().set(1.05f, 2.05f, 1.05f, 1.03f, 1f, 0.5f));

        AssetManager assets = new AssetManager();
        assets.load("esfera.obj", Model.class);
        assets.finishLoading();

        Model sol_imaxe = assets.get("esfera.obj", Model.class);
        sol = new ModelInstance(sol_imaxe);
        sol.transform.setToScaling(0.15f, 0.15f, 0.15f); //sol

        Model mercurio_imaxe = assets.get("esfera.obj", Model.class);
        mercurio = new ModelInstance(mercurio_imaxe);
        mercurio.transform.setToScaling(.03f, .03f, .03f); //mercurio

        Model venus_imaxe = assets.get("esfera.obj", Model.class);
        venus = new ModelInstance(venus_imaxe);
        venus.transform.setToScaling(.05f, .04f, .04f); //venus

        Model tierra_imaxe = assets.get("esfera.obj", Model.class);
        tierra = new ModelInstance(tierra_imaxe);
        tierra.transform.setToScaling(.06f, .05f, .05f); //tierra

        Model marte_imaxe = assets.get("esfera.obj", Model.class);
        marte = new ModelInstance(marte_imaxe);
        marte.transform.setToScaling(.04f, .04f, .04f); //marte

        //engadir elementos ó array
        elementos_sistema_solar.add(sol);
        elementos_sistema_solar.add(venus);
        elementos_sistema_solar.add(mercurio);
        elementos_sistema_solar.add(tierra);
        elementos_sistema_solar.add(marte);
    }



    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camController.update();
        modelBatch.begin(camara3d);
        modelBatch.render(sol, environment);
        modelBatch.render(venus, environment);
        modelBatch.render(mercurio, environment);
        modelBatch.render(tierra, environment);
        modelBatch.render(marte, environment);

        //posicións
        for (int i = 0; i < posicions.length; i++) {
            System.out.println(posicions[i]);
            if (posicions[i].x <= 0 && posicions[i].y <= 0 && posicions[i].z >= 0) {
                posicions[i].x += 0.01f;
                posicions[i].y -= 0.001f;
                posicions[i].z += 0.01f;
            } else if (posicions[i].x >= 0 && posicions[i].y <= 0 && posicions[i].z >= 0) {
                posicions[i].x += 0.01f;
                posicions[i].y += 0.001f;
                posicions[i].z -= 0.01f;
            } else if (posicions[i].x >= 0 && posicions[i].y >= 0 && posicions[i].z <= 0 + 0.01f) {
                posicions[i].x -= 0.01f;
                posicions[i].y += 0.001f;
                posicions[i].z -= 0.01f;
            } else {
                posicions[i].x -= 0.01f;
                posicions[i].y -= 0.001f;
                posicions[i].z += 0.01f;
            }
        }

        mercurio.transform.setTranslation(posicions[0].x, posicions[0].y, posicions[0].z);
        venus.transform.setTranslation(posicions[1].x, posicions[1].y, posicions[1].z);
        tierra.transform.setTranslation(posicions[2].x, posicions[2].y, posicions[2].z);
        marte.transform.setTranslation(posicions[3].x, posicions[3].y, posicions[3].z);
        modelBatch.end();
    }

    @Override
    public void resize(int ancho, int alto) {
        float aspectRatio = (float) ancho / (float) alto;
        camara3d.viewportWidth = 2f * aspectRatio;
        camara3d.viewportHeight = 2f;
        camara3d.far = 700f;
        camara3d.near = 0.1f;
        camara3d.position.set(0, 0, 2f);
        camara3d.update();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        elementos_sistema_solar.clear();
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

    @Override
    public void show() {

    }


}
